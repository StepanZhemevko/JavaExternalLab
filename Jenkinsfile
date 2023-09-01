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
    withSonarQubeEnv('Your_SonarQube_Server_Name') {
    sh 'mvn sonar:sonar'
    }
                
            }


            }
        }
    }
 post {
        always {
            jacoco(execPattern: '**/target/jacoco.exec')
            archiveArtifacts(artifacts: '**/target/*.jar', allowEmptyArchive: true)
        }
    }
}

