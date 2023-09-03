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
                dir("Task2") {
                    sh 'mvn clean test'
                }
            }
        }

        stage('Build') {
            steps {
                dir("Task2") {
                    sh 'mvn clean package'
                    sh 'mvn clean install'
                }
            }
        }

        stage('SonarQube Analysis') {
            steps {
                dir("Task2") {
                    withSonarQubeEnv('sonar') {
                        sh 'mvn clean verify sonar:sonar -Dsonar.java.binaries=target/classes'
                    }
                }
            }
        }

   stage('Compile-Package-create-war-file'){
def mvnHome = tool name: 'maven', type: 'maven'
//bat "${mvnHome)/bin/mvn package"
}
    }

    post {
        always {
            jacoco(execPattern: '**/target/jacoco.exec')
        }
    }
}
