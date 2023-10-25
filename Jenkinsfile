import groovy.json.JsonSlurperClassic

def env
def deploymentBatch
def deployAllowed = true
def testResult

pipeline {
    agent any
    parameters {
        text(name: 'REPOSITORIES', defaultValue: '{}', description: 'REPOS JSON')
        text(name: 'LOGS', defaultValue: '{}', description: 'LOGS JSON')
        text(name: 'DEPLOYMENT_SETUP', defaultValue: '{}', description: 'Input JSON with services and versions you want to deploy')
        choice(name: 'ENV', choices: ['SIT', 'SAT'], description: 'Pick environment to which you want to deploy')
    }
    stages {
        stage('Test AWS stuff'){
            steps{
                script{
                    def reposJson = new JsonSlurperClassic().parseText("${params.REPOSITORIES}")
                    def logsJson = new JsonSlurperClassic().parseText("${params.LOGS}")

                    reposJson.repositories.each {
                        repo ->
                            echo 'Name:' + repo.repositoryName
                    }

                    logsJson.services.each {
                        service ->
                            echo 'Name: ' + service.serviceName
                            echo 'Rolout: ' + service.deployments.rolloutState
                            echo 'Done at: ' + service.events[0].createdAt

                    }
                }
            }
        }
        stage('Validate artifacts') {
            steps {
                script {
                    env = "${params.ENV}"
                    deploymentBatch = new JsonSlurperClassic().parseText("${params.DEPLOYMENT_SETUP}")

                    echo 'Selected env: ' + env

                    deploymentBatch.artifact.each {
                        artifact ->
                            echo 'Retrieving versions from AWS...'
                            def versions = ['1.2.1-RELEASE', '2.4.25-RELEASE', '1.3.1-RELEASE', '1.3.1-HOTFIX']
                            echo 'Requested artifact deployemnt ' + artifact.name + ' : ' + artifact.version
                            if (versions.contains(artifact.version)) {
                                echo 'Deployment allowed for ' + artifact.name + ' : ' + artifact.version
                            } else {
                                echo 'Could not find ' + artifact.name + ' : ' + artifact.version + ' in ECS. Check your deployment setup'
                                deployAllowed = false;
                            }
                    }

                    if (deployAllowed) {
                        echo 'All checks passed, deployment approved'
                    } else {
                        error('Suggested config is not viable. Check log for details.')
                    }
                }
            }
        }
        stage('Update ECS') {
            steps {
                script {
                    if (deployAllowed) {
                        deploymentBatch.artifact.each {
                            artifact ->
                                echo 'Restarting service ' + artifact.name + ' : ' + artifact.version
                                echo "aws ecs update-service --cluster dummy-cluster --service $artifact.service --task-definition $artifact.name --platform-version $artifact.version --desired-count 1"
                        }
                        //TODO: add wait for restart ended?
                    } else {
                        echo('Skip deployment due to previous failures')
                    }
                }
            }
        }
        stage('Run tests') {
            steps {
                catchError {
                    withMaven(maven: 'default') {
                        sh 'mvn clean test'
                    }
                }
                script {
                    testResult = currentBuild.result
                }
            }
            post {
                always {
                    allure includeProperties: false, jdk: '', results: [[path: '**/allure-results']]
                }
            }
        }
        stage('Deployment') {
            steps {
                script {
                    if ('FAILURE'.equalsIgnoreCase(testResult)) {
                        error('Abort deployment due to test failures')
                    } else {
                        echo 'Deploying....'
                    }
                }
            }
        }
    }
}

env = null
deploymentBatch = null
deployAllowed = null