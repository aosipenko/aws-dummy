pipeline {
    agent any
    parameters {
        text(name: 'DEPLOYMENT_SETUP', defaultValue: '{}', description: 'Input JSON with services and versions you want to deploy')
        choice(name: 'ENV', choices: ['SIT', 'SAT'], description: 'PIck environment to which you want to deploy')
    }
    stages {
        stage('Example') {
            def setupJson = "${params.DEPLOYMENT_SETUP}"
            def env = "${params.ENV}"
            def jsonObj = readJSON text: setupJson
            steps {
                jsonObj.artifact.each {
                    artifact ->
                        echo 'processing artifact ' + artifact
                }
                echo "Deployment json: ${params.CHOICE}"
                echo "Selected env: ${params.ENV}"
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