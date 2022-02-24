def gv

pipeline {
    agent any
    stages {
        stage("init") {
            steps {
                script {
                    gv = load "script.groovy"
                }
            }
        }
        stage("build jar") {
            when {
                expression {
                    BRANCH_NAME == 'jenkins-jobs'
                }
            }
            steps {
                script {
                    echo "building jar"
                    //buildjar()
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
                    echo "building image"
                    //buildimage 'sujadocker14/java-maven-app:jma-1.0'
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
