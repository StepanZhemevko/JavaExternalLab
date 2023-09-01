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
    }

    post {
        always {
            // Publish JUnit test results
            junit '**/target/surefire-reports/*.xml'
            
            // Archive your built artifacts (JAR files)
            archiveArtifacts '**/target/*.jar'
            
            // Publish JaCoCo code coverage reports
            jacoco(execPattern: '**/target/jacoco.exec')

            // Publish SonarQube analysis results
            withSonarQubeEnv('sonar') {
                sh 'mvn sonar:sonar'
            }
        }
    }
}
