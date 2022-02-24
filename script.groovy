
<<<<<<< HEAD
=======
def buildImage() {
    echo "building the docker image..."
    withCredentials([usernamePassword(credentialsId: 'dockerHub_credentials', passwordVariable: 'PASS', usernameVariable: 'USER')]) {
        sh 'docker build -t sujadocker14/java-maven-app:jma-2.0 .'
        sh "echo $PASS | docker login -u $USER --password-stdin"
        sh 'docker push sujadocker14/java-maven-app:jma-2.0'
    }
} 
>>>>>>> e40b951d51045fc40307f19290842c22b2b38cd8

def deployApp() {
    echo 'deploying the application...'
} 

return this
