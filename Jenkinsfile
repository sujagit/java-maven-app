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
            stage("test") {
                        steps {
                            script {
                               echo "testing application"
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
                    echo "building jar "
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
                    echo "building image"
                    buildimage 'sujadocker14/java-maven-app:jma-1.0'
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