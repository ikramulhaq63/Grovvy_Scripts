def vulnerability(Map config = [:]) {
    loadScript (name: 'trivy.sh')
    sh "./trivy.sh ${config.imageName} ${config.severity} ${config.exitCode}"
}