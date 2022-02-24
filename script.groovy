def buildJar() {
    echo "Building jar file for .."
    sh 'mvn package'
}
def deployApp() {
    echo 'deploying the application...'
} 

return this
