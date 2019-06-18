import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.31"
}
//
group = "com.gamasoft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    testCompile("com.willowtreeapps.assertk:assertk-jvm:0.17")
    testCompile("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntime("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}