def call() {
    /* def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()*/

    def matcher = readFile('pom.xml') =~ '<version>(.+?)</version>'
    def pomVersion = matcher ? matcher[0][1] : null


    sh "echo 'Version: ${pomVersion}...'"
}