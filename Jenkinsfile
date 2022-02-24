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
                    echo 'incrementing app version'
                    sh 'mvn build-helper:parse-version versions:set\
                       -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} versions:commit'
                 def matcher = readFile('pom.xml') =~ '<version>(.+)<version>'
                 def version = matcher [0][1]
                 env.image_name = "$version-$BUILD_NUMBER"
                }
            }
        }

        stage("build jar") {
            steps {
                script {
                    echo "Building jar file"
                       sh 'mvn clean package'
                }
            }
        }
        stage("build image") {
            steps {
                script {
                   echo "building docker image "
                       withCredentials([usernamePassword(credentialsId: 'dockerHub_credentials', passwordVariable: 'pass', usernameVariable: 'user')]) {
                           sh "docker build -t sujadocker14/java-maven-app:${image_name} ."
                           sh "echo $pass | docker login -u $user --password-stdin"
                           sh "docker push sujadocker14/java-maven-app:${image_name} "
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
