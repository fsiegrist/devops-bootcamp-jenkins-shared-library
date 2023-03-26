#!/usr/bin/env groovy
def call(String gitRepo, String credentialsId, String branch) {
    withCredentials([usernamePassword(credentialsId: "${credentialsId}", usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh 'git config user.email "jenkins@example.com"'
        sh 'git config user.name "jenkins"'

        sh "git remote set-url origin https://${USERNAME}:${PASSWORD}@${gitRepo}"
        sh 'git add .'
        sh 'git commit -m "ci: version bump"'
        sh "git push origin HEAD:${branch}"

        sh 'git config --unset user.email'
        sh 'git config --unset user.name'
    }
}