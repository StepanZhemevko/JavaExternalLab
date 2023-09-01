pipeline {
    agent any

    triggers {
        pollSCM('*/5 * * * *')
    }

    stages {
        stage('Checkout') {
            steps {
            
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                dir("Task2"){
    sh 'mvn clean test'
    }
                
            }
        }

        stage('Build') {
            steps {
                dir("Task2"){
    sh 'mvn clean package'
    }
                
            }
        }
    }

}

