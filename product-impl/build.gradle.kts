
dependencies {
    implementation(project(":product-api"))
    // FIXME this relationship should not be here, it only is for demonstrating the "method call" integration
    // which is anyway not recommended.
    implementation(project(":notification-api"))
    implementation("org.springframework.modulith:spring-modulith-events-api")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.h2database:h2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
}



