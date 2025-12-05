pipeline {
    agent any

    parameters {
        string(name: 'BROWSER', defaultValue: 'chrome', description: 'Browser to run tests')
        string(name: 'ENV', defaultValue: 'qa', description: 'Environment to run tests')
        string(name: 'GROUPS', defaultValue: 'smoke', description: 'TestNG groups to run')
    }

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build Framework Core') {
            steps {
                // Build parent + core, skip tests for speed
                sh 'mvn -B -pl automation-framework-core clean install -DskipTests'
            }
        }

        stage('Run Yatra Tests') {
            steps {
                sh """
                    mvn -B -pl yatra-tests test \
                      -Denv=${ENV} \
                      -Dbrowser=${BROWSER} \
                      -Dgroups=${GROUPS}
                """
            }
        }

        stage('Publish Reports') {
            steps {
                // Publish surefire XMLs as JUnit results
                junit 'yatra-tests/target/surefire-reports/*.xml'

                // Archive all target outputs (optional but nice)
                archiveArtifacts artifacts: 'yatra-tests/target/**/*', fingerprint: true
            }
        }
    }
}
