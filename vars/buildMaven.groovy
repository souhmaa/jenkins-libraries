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
                def version = buildVersion {
                    suffix = 'ABC'
                }
                sh "echo 'building ${config.projectName} ... ${version}'"
            }
            stage('Tests') {
                parallel 'static': {
                    sh "echo 'shell scripts to run static tests...'"
                },
                        'unit': {
                            sh "echo 'shell scripts to run unit tests...'"
                        },
                        'integration': {
                            sh "echo 'shell scripts to run integration tests...'"
                        }
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