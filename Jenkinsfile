pipeline {

    agent any

    tools {
        maven 'Maven-3.9'
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build') {
            steps {
                sh 'docker build -t warehouse-inventory:latest .'
            }
        }

        stage('Docker Deploy') {
            steps {
                sh '''
                    docker stop warehouse-inventory || true
                    docker rm warehouse-inventory || true

                    docker run -d \
                    --name warehouse-inventory \
                    -p 8080:8080 \
                    warehouse-inventory:latest
                '''
            }
        }
    }

    post {
        success {
            echo 'DEPLOYMENT SUCCESSFUL'
        }

        failure {
            echo 'BUILD FAILED'
        }
    }
}
