plugins {
    id("org.springframework.boot") version "3.5.7"
}

dependencies {
    implementation(project(":product-api"))
    implementation(project(":product-impl"))
    implementation(project(":notification-api"))
    implementation(project(":notification-impl"))

    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("org.springframework.modulith:spring-modulith-events-api")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}



