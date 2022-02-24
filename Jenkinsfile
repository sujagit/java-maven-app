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
                    echo "Building jar file"
                       sh 'mvn package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                   echo "building docker image "
                       withCredentials([usernamePassword(credentialsId: 'dockerHub_credentials', passwordVariable: 'pass', usernameVariable: 'user')]) {
                           sh "docker build -t sujadocker14/java-maven-app:test-1.0 ."
                           sh "echo $pass | docker login -u $user --password-stdin"
                           sh 'docker push sujadocker14/java-maven-app:test-1.0 '
                       }

                }
            }
        }
        stage("deploy") {
            steps {
                script {
                    echo "deploying"
                }
            }
        }
    }   
}
