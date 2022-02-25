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
                    echo 'incrementing application version'
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
                                       withCredentials([usernamePassword(credentialsId: 'dockerHub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
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
        stage('commit version update') {
                    steps {
                        script {
                            withCredentials([usernamePassword(credentialsId: 'gitCredentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
                                // git config here for the first time run
                                sh 'git config --global user.email "jenkins@example.com"'
                                sh 'git config --global user.name "jenkins"'

                                sh "git remote set-url origin https://${USER}:${PASS}@github.com/sujagit/java-maven-app.git"
                                sh 'git add .'
                                sh 'git commit -m "ci: version bump"'
                                sh 'git push origin HEAD:jenkins-jobs'
                            }
                        }
                    }
        }
    }   
}
