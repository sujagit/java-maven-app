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
                    gv.buildJar()
                }
            }
        }
        stage('build image') {
            steps {
                script {
                      echo "building the docker image..."
                      gv.buildImage()
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
                            echo ' commit new version'

                        }
                    }
        }
    }
}

