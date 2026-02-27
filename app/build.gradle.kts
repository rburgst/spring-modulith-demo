plugins {
    id("org.springframework.boot") version "4.0.3"
}

dependencies {
    implementation(project(":product-api"))
    implementation(project(":product-impl"))
    implementation(project(":notification-api"))
    implementation(project(":notification-impl"))
    implementation(project(":inventory-api"))
    implementation(project(":inventory-impl"))

    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("org.springframework.modulith:spring-modulith-events-api")
    implementation("org.springframework.boot:spring-boot-starter-webmvc")
    implementation("com.h2database:h2")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}



