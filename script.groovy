def incrementVersion()
{
    sh 'mvn build-helper:parse-version versions:set \
                                            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                                            versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_NAME = "$version-$BUILD_NUMBER"

}
def buildJar() {
    echo "Building jar file for .."
    sh 'mvn package'
}
def buildImage() {
    echo "building image ..."
    withCredentials([usernamePassword(credentialsId: 'dockerHub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t sujadocker14/java-maven-app:${IMAGE_NAME} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push sujadocker14/java-maven-app:${IMAGE_NAME}"
    }
}
def deployApp() {
    echo 'deploying the application...'
    def dockerCmd = "docker run -d -p 8080:8080 --name java-maven-app${IMAGE_NAME} "
    sshagent(['Ec2-server-ssh']) {
        sh "ssh -o StrictHostKeyChecking=no ec2-user@54.172.66.25 ${dockerCmd}"

    }
}

def commitNewVersion() {
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

return this
