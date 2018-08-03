def call() {
    /* def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()*/
    node {
        def pom = readMavenPom file: 'pom.xml'
        def version = pom.version.replace("-SNAPSHOT", ".${currentBuild.number}")


        sh "echo 'Version: ${version}...'"
    }
}