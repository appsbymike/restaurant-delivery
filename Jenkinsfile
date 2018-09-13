pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'mvn clean install -DskipTests=true'
      }
    }
    stage('Deploy') {
      parallel {
        stage('Deploy') {
          steps {
            sh 'mvn deploy -DskipTests=true'
          }
        }
        stage('Sonarqube Scan') {
          steps {
            sh 'mvn sonar:sonar'
          }
        }
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test'
      }
    }
  }
}