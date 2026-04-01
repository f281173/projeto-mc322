plugins {

    application
}

repositories {
    mavenCentral()
}

dependencies {

    testImplementation(libs.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    

}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {

    mainClass = "mc322.jogo.App"
}

tasks.named<Test>("test") {

    useJUnitPlatform()
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}