import groovy.json.JsonSlurperClassic

def env
def deploymentBatch
def deployAllowed = true

pipeline {
    agent any
    parameters {
        text(name: 'DEPLOYMENT_SETUP', defaultValue: '{}', description: 'Input JSON with services and versions you want to deploy')
        choice(name: 'ENV', choices: ['SIT', 'SAT'], description: 'Pick environment to which you want to deploy')
    }
    stages {
        stage('Example') {
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
                        echo 'Suggested confid is not viable. Check log for details.'
                    }
                }
            }
        }
        stage('Setup ECS') {
            steps {
                echo 'picking ecs'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}