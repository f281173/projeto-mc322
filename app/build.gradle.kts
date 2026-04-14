plugins {

    application
    jacoco
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

    finalizedBy(tasks.named("jacocoTestReport"))
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // Garante que os testes correm antes de medir a cobertura
    
    reports {
        html.required.set(true)
        xml.required.set(false)
        csv.required.set(false)
    }
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}

tasks.withType<Javadoc> {
    options {
        this as StandardJavadocDocletOptions
        encoding = "UTF-8"
        charSet = "UTF-8"
        isAuthor = true
        isVersion = true
        addStringOption("Xdoclint:none", "-quiet")
    }
}