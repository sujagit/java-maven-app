def buildJar() {
    echo "Building jar file for .."
    sh 'mvn package'
}
def buildImage() {
    echo "building image ..."
    withCredentials([usernamePassword(credentialsId: 'dockerHub_credentials', passwordVariable: 'pass', usernameVariable: 'user')]) {
        sh "docker build -t sujadocker14/java-maven-app:test-1.0 ."
        sh "echo $pass | docker login -u $user --password-stdin"
        sh 'docker push sujadocker14/java-maven-app:test-1.0 '
    }
}
def deployApp() {
    echo 'deploying the application...'
} 

return this
