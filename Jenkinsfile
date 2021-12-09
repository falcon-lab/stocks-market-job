pipeline {
    agent {label "linux"}
    stages {
        stage('for the PR') {
            when {
                branch 'PR-*'
            }
            steps {
                echo 'this only runs for the PRs'
            }
        }
    }
}
