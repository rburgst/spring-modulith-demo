plugins {
    id("org.springframework.boot") version "3.5.7"
}

dependencies {
    implementation(project(":product"))
    implementation(project(":notification"))
    implementation(project(":inventory"))

    implementation("org.springframework.modulith:spring-modulith-starter-core")
    implementation("org.springframework.modulith:spring-modulith-events-api")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.h2database:h2")

    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.modulith:spring-modulith-starter-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.named<org.springframework.boot.gradle.tasks.run.BootRun>("bootRun") {
    jvmArgs = listOf(
        "--add-opens", "com.gofore.springmodulithdemo.product/com.gofore.springmodulithdemo.product.impl=ALL-UNNAMED",
        "--add-opens", "com.gofore.springmodulithdemo.inventory/com.gofore.springmodulithdemo.inventory.impl=ALL-UNNAMED",
        "--add-opens", "com.gofore.springmodulithdemo.app/com.gofore.springmodulithdemo=ALL-UNNAMED"
    )
}



