#!/usr/bin/env groovy
def gv

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage("init") {
                    steps {
                        script {
                            gv = load "script.groovy"
                        }
                    }
        }

        stage ("increment version") {
            steps {
                script {
                    echo 'Incrementing the application version'
                    gv.incrementVersion()
                }
            }
        }

        stage("build jar") {
            steps {
                script {
                    echo "Building Jar file"
                    gv.buildJar()
                }
            }
        }
        stage('build image') {
            steps {
                script {
                      echo 'Building Docker Image and pushed to Docker Hub'
                     // gv.buildImage()
                     gv.pushImagetoECR()
                }
            }
        }
        stage('deploy') {
            steps {
                script {
                    echo 'Docker Imaged Deployed to EC2 Server Instance'
                     //gv.deployApp()
                     gv.deployAppfromECR()

               }
           }
        }
        stage('commit version update') {
                    steps {
                        script {
                            echo 'New version commited in GitHub'
                            gv.commitNewVersion()

                        }
                    }
        }
    }
}