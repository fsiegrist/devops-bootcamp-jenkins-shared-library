#!/usr/bin/env groovy
def call(String appDir, String versionPart) {
    echo "incrementing ${versionPart} version..."
    dir("${appDir}") {
        sh "npm version ${versionPart}"

        def packageJson = readJSON file: 'package.json'
        def version = packageJson.version

        env.IMAGE_VERSION = "$version-$BUILD_NUMBER"
    }
}