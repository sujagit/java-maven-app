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
<<<<<<< HEAD
                   buildimage 'sujadocker14/java-maven-app:jma-1.0'
=======
                   buildimage()
>>>>>>> e40b951d51045fc40307f19290842c22b2b38cd8
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
