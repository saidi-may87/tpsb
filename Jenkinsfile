pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/saidi-may87/tpsb.git'
            }
        }
        stage('Build & Test') {
            steps {
                sh 'apt-get update -qq && apt-get install -y -qq maven > /dev/null; mvn -B clean verify'
            }
        }
        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube') {
                    sh 'mvn -B org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=tpsb -Dsonar.projectName=tpsb'
                }
            }
        }
        stage('Docker Build') {
            steps {
                sh 'docker build -t tpsb:${BUILD_NUMBER} .'
            }
        }
    }
}
