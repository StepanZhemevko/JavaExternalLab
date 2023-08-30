pipeline {
    agent any
    options {
        skipStagesAfterUnstable()
    }
    environment {
        // Define the path to the Maven Local Repository
        MAVEN_LOCAL_REPO = '/home/stepan/.m2'
    }
    stages {
        stage('Build') {
            steps {
                dir("${MAVEN_LOCAL_REPO}") {
                    sh 'mvn -B -Dmaven.repo.local=${MAVEN_LOCAL_REPO} -DskipTests clean package'
                }
            }
        }
        stage('Test') {
            steps {
                dir("${MAVEN_LOCAL_REPO}") {
                    sh 'mvn -Dmaven.repo.local=${MAVEN_LOCAL_REPO} test'
                }
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Deliver') {
            steps {
                dir("${MAVEN_LOCAL_REPO}") {
                    sh './jenkins/scripts/deliver.sh'
                }
            }
        }
    }
}
