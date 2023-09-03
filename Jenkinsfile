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
                    def tomcatServer = 'Your_Tomcat_Server_Name' // Replace with your Tomcat server name
                    def tomcatCredentialsId = 'TomcatCreds' // Replace with your credentials ID for Tomcat

                    // Define the path to the WAR file you want to deploy
                    def warFilePath = "Task2/target/*.war"

                    // Deploy the WAR file to Tomcat using the Tomcat Deploy plugin
                    tomcatDeploy(
                        serverName: tomcatServer,
                        credentialsId: tomcatCredentialsId,
                        war: warFilePath
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
