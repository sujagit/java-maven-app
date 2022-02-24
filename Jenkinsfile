#!/usr/bin/env groovy
@Library('jenkins-shared-library')

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
        stage("build jar") {
            steps {
                script {
                    buildjar()
                }
            }
        }
        stage("build image") {
            steps {
                script {
                   buildimage()
                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                    gv.deployApp()
                }
            }
        }
    }   
}
