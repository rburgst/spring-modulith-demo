dependencies {
    implementation(project(":notification"))
    implementation("org.springframework.modulith:spring-modulith-events-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")
    implementation("jakarta.enterprise:jakarta.enterprise.cdi-api:4.0.1")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}

