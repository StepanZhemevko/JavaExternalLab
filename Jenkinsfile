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
    withSonarQubeEnv('sonar') {
    sh 'mvn sonar:sonar'
    }
                
            }


            }
        }
    }
 post {
        always {
            junit '**/target/surefire-reports/*.xml'
     
            archiveArtifacts '**/target/*.jar'
            
            jacoco(execPattern: '**/target/jacoco.exec')

            withSonarQubeEnv('sonar') {
                sh 'mvn sonar:sonar'
        }
    }
}

