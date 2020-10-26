import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.0"
    application
}
group = "com.test"
version = "1.0-SNAPSHOT"

val tornadofx_version: String by rootProject

repositories {
    mavenCentral()
}

application {
    mainClassName = "com.example.MainKt"
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("no.tornado:tornadofx:$tornadofx_version")
    implementation("org.slf4j:slf4j-simple:1.6.1")
    implementation("io.github.microutils:kotlin-logging:1.6.22")
    implementation("mysql","mysql-connector-java","8.0.21")
    testImplementation(kotlin("test-junit"))
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}