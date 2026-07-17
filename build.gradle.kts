plugins {
    id("java")
}

group = "org.super.tier"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("org.liquibase:liquibase-core")
    implementation("org.springframework:spring-context:6.1.5")
    implementation("org.springframework:spring-tx:6.1.5")
    implementation("org.springframework.data:spring-data-jpa:3.2.5")

}

tasks.test {
    useJUnitPlatform()
}