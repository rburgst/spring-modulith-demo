plugins {
    java
    id("org.springframework.boot") version "3.5.7"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com.gofore"
version = "0.0.1-SNAPSHOT"
description = "spring-modulith-demo"

allprojects {
    repositories {
        mavenCentral()
    }
}

extra["springModulithVersion"] = "1.4.4"

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.7")
        mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
    }
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "io.spring.dependency-management")

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(21)
        }
    }

    extra["springModulithVersion"] = "1.4.4"

    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:3.5.7")
            mavenBom("org.springframework.modulith:spring-modulith-bom:${property("springModulithVersion")}")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

// Root project doesn't need dependencies - the app module contains the main application
