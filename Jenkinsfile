def gv

pipeline {
    agent any
    tools {
        maven 'maven'
    }
    stages {
        stage ("increment version") {
            steps {
                script {
                    echo 'incrementing app version...'
                                        sh 'mvn build-helper:parse-version versions:set \
                                            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                                            versions:commit'
                                        def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
                                        def version = matcher[0][1]
                                        env.IMAGE_NAME = "$version-$BUILD_NUMBER"

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
                      echo "building the docker image..."
                                       withCredentials([usernamePassword(credentialsId: 'docker-hub-repo', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                                           sh "docker build -t sujadocker14/java-maven-app:${IMAGE_NAME} ."
                                           sh "echo $PASS | docker login -u $USER --password-stdin"
                                           sh "docker push sujadocker14/java-maven-app:${IMAGE_NAME}"

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
