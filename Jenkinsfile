import groovy.json.JsonSlurper

pipeline {
    agent any
    parameters {
        text(name: 'DEPLOYMENT_SETUP', defaultValue: '{}', description: 'Input JSON with services and versions you want to deploy')
        choice(name: 'ENV', choices: ['SIT', 'SAT'], description: 'Pick environment to which you want to deploy')
    }
    stages {
        stage('Example') {
            script {
                def setupJson = "${params.DEPLOYMENT_SETUP}"
                def env = "${params.ENV}"
                def jsonObj = new JsonSlurper().parseText(setupJson)

                jsonObj.artifact.each {
                    artifact ->
                        echo 'processing artifact ' + artifact
                }
            }
            steps {
                echo "Deployment json: ${params.CHOICE}"
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