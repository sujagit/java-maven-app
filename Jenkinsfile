#!/usr/bin/env groovy
library identifier: 'jenkins-shared-library@master', retriever: modernSCM(
    [$class: 'GitSCMSource',
     remote: 'https://gitlab.com/nanuchi/jenkins-shared-library.git',
     credentialsId: 'gitlab-credentials'
    ]
)

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
                        def dockerCmd = "docker run -d -p 3000:3080 --name react-node-app sujadocker14/react-nodejs-app:2.0 "
                         echo 'commiting new version in repo'
                         sshagent (credentials: ['54.198.183.80']) {
                             sh "ssh -o StrictHostKeyChecking=no -l ec2-user@54.198.183.80 ${dockerCmd}"
                           }

                        }
                    }
        }
    }
}

