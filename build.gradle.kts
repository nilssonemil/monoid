plugins {
    id("java")
    id("maven-publish")
}

java.sourceCompatibility = org.gradle.api.JavaVersion.VERSION_19
java.targetCompatibility = org.gradle.api.JavaVersion.VERSION_19

group = "solutions.shitops"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.assertj:assertj-core:3.7.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "solutions.shitops"
            artifactId = "monoid"
            version = "1.0-SNAPSHOT"

            from(components["java"])
        }
    }
}
