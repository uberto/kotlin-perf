import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.70"
    id("me.champeau.gradle.jmh") version "0.5.0"
}

group = "com.gamasoft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

jmh {
    jmhVersion = "1.23"

//    includeTests = true
    jvmArgs = listOf(
        "-Xms6g",
        "-Xmx6g",
        "-Dgraal.ShowConfiguration=info",
        "-XX:+AlwaysPreTouch",
        "-XX:+UnlockExperimentalVMOptions",
        "-XX:+UseJVMCICompiler"
    )
    warmupForks = 0
    fork = 2
    warmupIterations = 5
    iterations = 5
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testImplementation("com.willowtreeapps.assertk:assertk-jvm:0.17")
    testImplementation("org.openjdk.jmh:jmh-core:1.23")
    testImplementation("org.openjdk.jmh:jmh-generator-annprocess:1.23")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.4.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.4.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
