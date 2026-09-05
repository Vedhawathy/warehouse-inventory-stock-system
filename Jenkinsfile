pipeline {
    agent any

    tools {
        jdk 'JDK-21'
        maven 'Maven-3.9'
    }

    stages {

        stage('Build') {
            steps {
                sh 'java -version'
                sh 'javac -version'
                sh 'mvn -version'
                sh 'mvn clean package -DskipTests'
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
            echo 'BUILD SUCCESS'
        }
        failure {
            echo 'BUILD FAILED'
        }
    }
}
