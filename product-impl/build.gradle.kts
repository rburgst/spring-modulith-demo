plugins {
    id("org.springframework.boot")
}

dependencies {
    implementation(project(":product-api"))
    implementation(project(":notification-api"))
    implementation("org.springframework.modulith:spring-modulith-events-api")
    implementation("org.springframework:spring-context")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}



