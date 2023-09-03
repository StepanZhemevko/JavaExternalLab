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
        stage ('Deployment') {
            steps {
                dir("Task2") {
deploy adapters: [tomcat10 (credentials Id: 'TomcatCreds', path: , url: 'http://localhost:8080/')], contextPath: null, war: '**/target/*.war'
                }
            }
        }
        
    }

    post {
        always {
            jacoco(execPattern: '**/target/jacoco.exec')
        }
    }
}
