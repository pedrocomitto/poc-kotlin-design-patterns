plugins {
    id("org.jetbrains.kotlin.jvm") version ("1.4.10")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")

    implementation("org.junit.jupiter:junit-jupiter-api:5.3.2")
    runtimeOnly("org.junit.jupiter:junit-jupiter-engine:5.3.2")
//    implementation("org.assertj:assertj-core:3.11.1")
}
