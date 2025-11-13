plugins {
    java
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

// Task to validate module dependencies
tasks.register("validateModuleDependencies") {
    group = "verification"
    description = "Validates that notification module doesn't depend on product module"
    
    doLast {
        val errors = mutableListOf<String>()
        
        // Helper function to get project name from dependency
        fun getProjectName(dep: ProjectDependency): String {
            val path = dep.dependencyProject.path
            return path.substring(1) // Remove leading ':'
        }
        
        // Rule: Notification module should not depend on product module
        subprojects.forEach { project ->
            if (project.name == "notification") {
                project.configurations.getByName("implementation").dependencies
                    .filterIsInstance<ProjectDependency>()
                    .forEach { dep ->
                        val depProjectName = getProjectName(dep)
                        if (depProjectName == "product") {
                            errors.add("❌ ${project.name} depends on product module. Notification module should not depend on product module.")
                        }
                    }
            }
        }
        
        if (errors.isNotEmpty()) {
            println("\n${"=".repeat(80)}")
            println("MODULE DEPENDENCY VALIDATION FAILED")
            println("${"=".repeat(80)}\n")
            errors.forEach { println(it) }
            println("\n${"=".repeat(80)}\n")
            throw GradleException("Module dependency validation failed. See errors above.")
        } else {
            println("\n${"=".repeat(80)}")
            println("✅ MODULE DEPENDENCY VALIDATION PASSED")
            println("${"=".repeat(80)}\n")
            println("✓ Notification module does not depend on product module")
            println("✓ All dependency rules are satisfied\n")
        }
    }
}

// Make validateModuleDependencies run before build
tasks.named("build") {
    dependsOn("validateModuleDependencies")
}
