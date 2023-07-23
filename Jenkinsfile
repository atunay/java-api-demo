pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Clone the source code from the local directory (use your local path)
                dir('/home/tunay/java-api-demo/target/classes/tests/RegistrationAndLogin') {
                    // Replace 'your-project-directory' with the path to your cloned project
                    git url: 'src/main/java/tests/RegistrationAndLogin.java', branch: 'master'
                }
            }
        }

        stage('Build') {
            steps {
                // Build the project using Maven
                dir('/home/tunay/java-api-demo/target/classes/tests/RegistrationAndLogin') {
                    // Replace 'your-project-directory' with the path to your cloned project
                    sh 'mvn clean package'
                }
            }
        }

        stage('Test') {
            steps {
                // Run the test suite using TestNG
                dir('/home/tunay/java-api-demo/target/classes/tests/RegistrationAndLogin') {
                    // Replace 'your-project-directory' with the path to your cloned project
                    sh 'mvn test'
                }
            }
        }

        stage('Archive Test Results') {
            steps {
                // Archive test reports for Jenkins to display
                junit '/home/tunay/java-api-demo/target/surefire-reports/*.xml'
            }
        }
    }
}
