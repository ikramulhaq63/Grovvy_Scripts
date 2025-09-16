def vulnerability(String imageName) {
    sh """
        echo image - ${imageName}

        trivy image ${imageName} \
        --severity LOW,MEDIUM,HIGH \
        --exit-code 0 \
        --quiet \
        --format json -o trivy-image-MEDIUM-report.json

        trivy image ${imageName} \
        --severity CRITICAL \
        --exit-code 1 \
        --quiet \
        --format json -o trivy-image-CRITICAL-report.json
    """
}

def reportConvertor() {
    sh '''
        trivy convert \
            --format template --template "@/usr/local/share/trivy/templates/html.tpl" \
            --output trivy-image-MEDIUM-report.html trivy-image-MEDIUM-report.json
        trivy convert \
            --format template --template "@/usr/local/share/trivy/templates/html.tpl" \
            --output trivy-image-CRITICAL-report.html trivy-image-CRITICAL-report.json
        trivy convert \
            --format template --template "@/usr/local/share/trivy/templates/junit.tpl" \
            --output trivy-image-MEDIUM-report.xml trivy-image-MEDIUM-report.json
        trivy convert \
            --format template --template "@/usr/local/share/trivy/templates/junit.tpl" \
            --output trivy-image-CRITICAL-report.xml trivy-image-CRITICAL-report.json
    '''
}