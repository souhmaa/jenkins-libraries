def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    def pom = readMavenPom file: 'pom.xml'
    def pomVersion = pom.version


    echo pomVersion
}