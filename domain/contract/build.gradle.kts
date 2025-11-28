plugins {
    alias(libs.plugins.java.library)
    alias(libs.plugins.kotlin)
}

kotlin {
    jvmToolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    api(project(":domain:model"))
    api(libs.kotlinx.coroutines.core)
}
