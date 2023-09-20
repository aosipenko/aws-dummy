pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                withMaven(
                        maven: 'default',
                        mavenLocalRepo: '.repository'
                )
                sh "mvn clean install"
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