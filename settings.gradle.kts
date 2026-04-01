plugins {
    // Baixa o Java automaticamente se for necessário
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}

// O nome oficial do teu projeto e a indicação de que a pasta 'app' existe
rootProject.name = "projeto-mc322"
include("app")