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
                 Maven mvn = new Maven(this)
            mvn.goals("clean test -B -P jenkins " +
                    "-Ddefault.test.suite=${test_suites}")
            }
        }

        stage('Build') {
            steps {
               
                sh 'mvn clean package'
            }
        }
    }

    post {
        always {
           
            junit '**/target/surefire-reports/*.xml'
        }
    }
}

