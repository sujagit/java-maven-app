
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
        stage('build image') {
            steps {
                script {
                      echo "building the docker image..."
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo 'deploy to ec2 server'
               }
           }
        }
        stage('commit version update') {
                    steps {
                        script {
                        def dockerCmd = "docker run -d -p 3080:3080 --name react-node-app sujadocker14/react-nodejs-app:1.0 "
                         echo 'commiting new version in repo'
                         sshagent(['ec2-server-key']) {
                            sh "ssh -o StrictHostKeyChecking=no ec2-user@54.198.183.80 ${dockerCmd}"
                         }

                        }
                    }
        }
    }
}

