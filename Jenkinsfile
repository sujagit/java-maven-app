
pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage("test") {
            steps {
                script {
                    echo "testing"
                    echo "testing the application for branch $BRANCH_NAME"
                }
            }
        }
        stage("build") {
            steps {
                script {
                    echo "building the application for branch $BRANCH_NAME"
                    buildjar()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    echo "deploying the application for branch $BRANCH_NAME"
                }
            }
        }
    }   
}
