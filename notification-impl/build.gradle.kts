plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":notification-api"))
    implementation("org.springframework.modulith:spring-modulith-events-api")
    implementation("org.springframework:spring-context")
    implementation("org.slf4j:slf4j-api")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

