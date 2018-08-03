def call(body) {
    def config = [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = config
    body()

    node {
        // Clean workspace before doing anything
        deleteDir()

        try {
            stage('Clone') {
                checkout scm
            }
            stage('Build') {
                sh "mvn clean package"
            }
            stage('Deploy') {
                sh "echo 'deploying to server ${config.serverDomain}...'"
            }
        } catch (err) {
            currentBuild.result = 'FAILED'
            throw err
        }
    }
}