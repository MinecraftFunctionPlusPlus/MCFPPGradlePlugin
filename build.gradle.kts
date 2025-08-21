val GROUP = "top.mcfpp"
val VERSION = "1.0.2-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.9.23"
    id("java-gradle-plugin")
    id("maven-publish")
}

group = GROUP
version = VERSION

repositories {
    mavenCentral()
    //mavenLocal()
    maven("https://nexus.mcfpp.top/repository/maven-public/")
    maven("https://jitpack.io")
    maven("https://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://libraries.minecraft.net")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("top.mcfpp:mcfpp:$VERSION")
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(21)
}

gradlePlugin {
    plugins {
        create("mcfpp-gradle") {
            id = "mcfpp-gradle"
            implementationClass = "top.mcfpp.gradle.MCFPPGradlePlugin"
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            groupId = GROUP
            artifactId = "mcfpp-gradle"
            version = VERSION
            pom {
                name.set("mcfpp-gradle")
                description.set("Gradle support for MCFPP")
            }
        }
    }
    repositories {
        //mavenLocal()
        maven {
            val baseUrl = "https://nexus.mcfpp.top"
            url = if (version.toString().endsWith("SNAPSHOT")) {
                uri("$baseUrl/repository/maven-snapshots/")
            }else{
                uri("$baseUrl/repository/maven-releases/")
            }
            credentials {
                username = project.findProperty("NEXUS_USERNAME") as String
                password = project.findProperty("NEXUS_PASSWORD") as String
            }
        }
    }
}