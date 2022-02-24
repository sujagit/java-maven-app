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
            when {
                expression {
                    BRANCH_NAME == 'jenkins-jobs'
                }
            }
            steps {
                script {
                   gv.buildJar()
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
                    gv.buildImage()

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
