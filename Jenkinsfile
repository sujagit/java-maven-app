
def gv

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage ("increment version") {
            steps {
                script {
                    echo 'incrementing application version'
                }
            }
        }

        stage("build jar") {
            steps {
                script {
                    echo "Building jar file"
                }
            }
        }
        stage("build image") {
            steps {
                script {
                      echo "building the docker image..."
                }
            }
        }
        stage('deploy to Ec2 server') {
            steps {
                script {
                    echo 'deploy'
               }
           }
        }
        stage('commit version update') {
                    steps {
                        script {
                         echo 'commiting new version in repo'

                        }
                    }
        }
    }
}

