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

        stage('Deployment') {
            steps {
                script {
                    def tomcatServer = 'tomcat' 
                    def tomcatCredentialsId = 'TomcatCreds' 

                    tomcatDeploy(
                        serverName: tomcat,
                        credentialsId: TomcatCreds,
                        war: '**/target/*.war'
                    )
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
