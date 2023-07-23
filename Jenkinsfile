pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the repository
                git 'https://github.com/atunay/java-api-demo.git'
            }
        }

        stage('Build') {
            steps {
                // Run your build commands here
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Run your tests here
                sh 'mvn test'
            }
        }
    }
}

