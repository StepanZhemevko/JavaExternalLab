pipeline {
    agent any

    triggers {
        pollSCM('*/5 * * * *') // Polls the repository every 5 minutes (adjust as needed)
    }

    stages {
        stage('Checkout') {
            steps {
                // Check out your Git repository
                checkout scm
            }
        }

        stage('Build and Test') {
            steps {
                // Build and test your project using Maven
                sh 'mvn clean test'
            }
        }

        stage('Build') {
            steps {
                // Build your project (e.g., create a JAR or WAR file)
                sh 'mvn clean package'
            }
        }
    }

    post {
        always {
            // Archive test results
            junit '**/target/surefire-reports/*.xml'
        }
    }
}

