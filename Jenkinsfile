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
<<<<<<< HEAD
=======
                    echo "building jar"
>>>>>>> 59d77d238c1dbbaa6af0203536006104f57e1340
                    buildjar()
                }
            }
        }
        stage("build image") {
        when {
        expression {
        BRANCH_NAME == 'jenkins-jobs'
        }
        }
            steps {
                script {
<<<<<<< HEAD
<<<<<<< HEAD
                   buildimage 'sujadocker14/java-maven-app:jma-1.0'
=======
                   buildimage()
>>>>>>> e40b951d51045fc40307f19290842c22b2b38cd8
=======
                    echo "building image"
                    buildimage 'sujadocker14/java-maven-app:jma-1.0'
>>>>>>> 59d77d238c1dbbaa6af0203536006104f57e1340
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
