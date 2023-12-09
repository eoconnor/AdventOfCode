plugins {
    kotlin("jvm") version "1.8.0"
    application
}

group = "com.eric.adventofcode"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        setUrl("https://jitpack.io")
    }
}

dependencies {
    implementation("com.github.jkcclemens:khttp:master-SNAPSHOT")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.8")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    //jvmToolchain(8) Don't appear to any versions of 8 available for M1 chips
    jvmToolchain(17)
}

application {
    mainClass.set("MainKt")
}