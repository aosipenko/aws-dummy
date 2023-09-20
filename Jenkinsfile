pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                withMaven(
                        maven: 'default'
                ) {
                    sh "mvn clean install"
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}