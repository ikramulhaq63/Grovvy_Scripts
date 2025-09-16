def call (Map config = [:]) {
    def scriptData = libraryResource("scripts/${config.name}")
    writeFile file: config.name, text: scriptData
    sh "chmod +x ./${config.name}"
}