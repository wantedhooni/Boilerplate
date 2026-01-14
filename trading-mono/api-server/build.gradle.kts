plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("java")
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.5.6")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api")
    annotationProcessor("jakarta.annotation:jakarta.annotation-api")
    // implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation(libs.uuid.creator)

    val querydslVersion = libs.versions.querydsl.get()
    implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
    annotationProcessor("com.querydsl:querydsl-apt:$querydslVersion:jakarta")

    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:3.0.1")





    implementation("org.springframework.boot:spring-boot-configuration-processor")
    developmentOnly("org.springframework.boot:spring-boot-devtools")




    testImplementation("org.springframework.boot:spring-boot-starter-test")

}
