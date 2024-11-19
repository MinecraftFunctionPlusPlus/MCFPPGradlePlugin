val GROUP = "top.mcfpp"
val VERSION = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.9.23"
    id("java-gradle-plugin")
    id("maven-publish")
}

group = GROUP
version = VERSION

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

gradlePlugin {
    plugins {
        create("mcfpp") {
            id = "top.mcfpp.gradle"
            implementationClass = "top.mcfpp.gradle.MCFPPGradlePlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = GROUP
            artifactId = "mcfpp"
            version = VERSION
        }
    }
    repositories {
        maven {
            url = uri("${layout.buildDirectory}/repo")
        }
    }
}