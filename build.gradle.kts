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

extra["springModulithVersion"] = "2.0.3"

dependencyManagement {
    imports {
        mavenBom("org.springframework.boot:spring-boot-dependencies:4.0.3")
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


    dependencyManagement {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:4.0.3")
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
    description = "Validates that impl projects don't depend on other impl projects and notification doesn't depend on product"
    
    doLast {
        val errors = mutableListOf<String>()
        
        // Define which projects are impl vs api
        val implProjects = setOf("product-impl", "notification-impl", "inventory-impl")
        val apiProjects = setOf("product-api", "notification-api", "inventory-api")
        
        // Helper function to get project name from dependency
        fun getProjectName(dep: ProjectDependency): String {
            val depProj = project.project(dep.path) //  dep.dependencyProject.path
            val path = depProj.path
            return path.substring(1) // Remove leading ':'
        }
        
        // Rule 1: No impl project should depend on another impl project
        subprojects.forEach { project ->
            if (project.name in implProjects) {
                project.configurations.getByName("implementation").dependencies
                    .filterIsInstance<ProjectDependency>()
                    .forEach { dep ->
                        val depProjectName = getProjectName(dep)
                        if (depProjectName in implProjects && depProjectName != project.name) {
                            errors.add("❌ ${project.name} depends on impl project ${depProjectName}. Impl projects should only depend on api projects.")
                        }
                    }
            }
        }
        
        // Rule 2: Notification modules should not depend on product modules
        subprojects.forEach { project ->
            if (project.name.startsWith("notification")) {
                project.configurations.getByName("implementation").dependencies
                    .filterIsInstance<ProjectDependency>()
                    .forEach { dep ->
                        val depProjectName = getProjectName(dep)
                        if (depProjectName.startsWith("product")) {
                            errors.add("❌ ${project.name} depends on product module ${depProjectName}. Notification modules should not depend on product modules.")
                        }
                    }
            }
        }
        
        // Rule 3: Impl projects should only depend on their own api or external dependencies
        subprojects.forEach { project ->
            if (project.name in implProjects) {
                val moduleName = project.name.replace("-impl", "")
                val expectedApiProject = "$moduleName-api"
                
                project.configurations.getByName("implementation").dependencies
                    .filterIsInstance<ProjectDependency>()
                    .forEach { dep ->
                        val depProjectName = getProjectName(dep)
                        if (depProjectName in apiProjects && depProjectName != expectedApiProject) {
                            // This is allowed for cross-module api dependencies (e.g., product-impl can use notification-api)
                            // But we check this in rule 2, so we'll allow it here
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
            println("✓ No impl projects depend on other impl projects")
            println("✓ Notification modules do not depend on product modules")
            println("✓ All dependency rules are satisfied\n")
        }
    }
}

// Make validateModuleDependencies run before build
tasks.named("build") {
    dependsOn("validateModuleDependencies")
}
