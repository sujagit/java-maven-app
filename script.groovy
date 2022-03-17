def incrementVersion()
{
    sh 'mvn build-helper:parse-version versions:set \
                                            -DnewVersion=\\\${parsedVersion.majorVersion}.\\\${parsedVersion.minorVersion}.\\\${parsedVersion.nextIncrementalVersion} \
                                            versions:commit'
    def matcher = readFile('pom.xml') =~ '<version>(.+)</version>'
    def version = matcher[0][1]
    env.IMAGE_TAG = "$version-$BUILD_NUMBER"
    env.IMAGE = "sujadocker14/java-maven-app:${IMAGE_TAG}"
    env.ecrIMAGE = "079537524733.dkr.ecr.us-east-1.amazonaws.com/java-maven-app:${IMAGE_TAG}"

}
def buildJar() {
    sh 'mvn package'
}
def buildImage() {
    withCredentials([usernamePassword(credentialsId: 'dockerHub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ${IMAGE} ."
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh "docker push ${IMAGE}"
    }
}
def pushImagetoECR() {
    withCredentials([usernamePassword(credentialsId: 'ecr_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh "docker build -t ${ecrIMAGE} ."
        sh "echo $PASS | docker login -u $USER --password-stdin 079537524733.dkr.ecr.us-east-1.amazonaws.com"
        sh "docker push ${ecrIMAGE}"
    }
}
def deployApp() {
    //def dockerCmd = "docker run -d -p 8080:8080 --name java-maven-app ${IMAGE} "
    //def dockerCmd = " docker-compose -f docker-compose.yaml up --detach"
    def shellCmd = "bash ./server-commands.sh ${IMAGE}"
    def ec2Instance = "ec2-user@34.230.82.30"
    sshagent(['aws-ec2-ssh']) {
        sh "scp server-commands.sh ${ec2Instance}:/home/ec2-user"
        sh "scp docker-compose.yaml ${ec2Instance}:/home/ec2-user"
        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${shellCmd}"

    }
}

def deployAppfromECR() {
    def dockerCmd = "docker run -d -p 8080:8080 --name java-maven-app ${ecrIMAGE} "
    def ec2Instance = "ec2-user@34.230.82.30"
    sshagent(['aws-ec2-ssh']) {

        sh "ssh -o StrictHostKeyChecking=no ${ec2Instance} ${dockerCmd}"

    }
}

def deployToKn8sNode() {
    sh 'envsubst < deployment.yaml | kubectl apply -f -'
    sh 'envsubst < service.yaml | kubectl apply -f -'
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
