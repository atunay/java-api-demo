pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout the source code from the Git repository
                git 'https://github.com/atunay/java-api-demo.git'
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                // Run the test suite using Maven
                sh 'mvn test'
            }
        }

        stage('Archive Test Results') {
            steps {
                // Archive test reports for Jenkins to display
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}

