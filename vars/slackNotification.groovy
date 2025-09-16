def call(String buildStatus = 'STARTED') {
    buildStatus = buildStatus ?: 'SUCCESS'
    // Default values
    def color = '#FFFF00' // Yellow
    def message = "${env.JOB_NAME} - Build #${env.BUILD_NUMBER} - ${buildStatus}\n${env.BUILD_URL}"

    if (buildStatus == 'SUCCESS') {
        color = '#00FF00' // Green
    } else if (buildStatus == 'FAILURE') {
        color = '#FF0000' // Red
    } else if (buildStatus == 'UNSTABLE') {
        color = '#FFA500' // Orange
    }

    slackSend(channel: 'jenkins-notification', color: color, message: message)
}
