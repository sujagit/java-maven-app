def buildJar() {
    echo "Building jar file for $BRANCH_NAME"
    sh 'mvn package'
}
def deployApp() {
    echo 'deploying the application...'
} 

return this
