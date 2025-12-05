pipeline {
    agent any

    tools {
        // Names must match JDK & Maven installations configured in Jenkins
        jdk 'jdk17'
        maven 'maven3'
    }

    parameters {
        choice(
            name: 'ENV',
            choices: ['qa', 'stage', 'prod-like'],
            description: 'Target environment for tests'
        )
        choice(
            name: 'BROWSER',
            choices: ['chrome', 'firefox'],
            description: 'Browser to use for UI tests'
        )
        choice(
            name: 'GROUPS',
            choices: ['smoke', 'regression', 'all'],
            description: 'TestNG groups to run'
        )
    }

    environment {
        // Optional: use these if you want defaults
        // ENV = "${params.ENV}"
        // BROWSER = "${params.BROWSER}"
        // GROUPS = "${params.GROUPS}"
    }

    stages {

        stage('Checkout') {
            steps {
                // This checks out the repo with Jenkinsfile + pom.xml + modules
                checkout scm
            }
        }

        stage('Build Framework Core') {
            steps {
                sh 'mvn -pl automation-framework-core clean install -DskipTests'
            }
        }

        stage('Run Yatra Tests') {
            steps {
                // Run only yatra-tests module with parameters
                sh """
                    mvn -pl yatra-tests clean test \
                      -Denv=${params.ENV} \
                      -Dbrowser=${params.BROWSER} \
                      -Dgroups=${params.GROUPS}
                """
            }
        }
    }

    post {
        always {
            // Publish TestNG results produced by Surefire
            junit 'yatra-tests/target/surefire-reports/*.xml'

            // Archive reports / screenshots if you add them later
            archiveArtifacts artifacts: 'yatra-tests/target/**/*', fingerprint: true
        }
    }
}
