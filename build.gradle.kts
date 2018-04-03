import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.30"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

apply {
    plugin("kotlin")
}

group = "org.jlleitschuh.testing"
version = "1.0-SNAPSHOT"


val kotlin_version: String by extra

repositories {
    mavenCentral()
}

dependencies {
    "compile"(kotlin("stdlib-jdk8", kotlin_version))
    "testCompile"("org.junit.jupiter:junit-jupiter-api:5.1.0")
    "testRuntime"("org.junit.jupiter:junit-jupiter-engine:5.1.0")
    "testRuntime"(create(group = "org.junit.platform", name = "junit-platform-launcher", version = "1.1.0"))
}

tasks.withType<Test> {
    testLogging {
        events(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.STARTED)
        displayGranularity = 0
        showExceptions = true
        showCauses = true
        showStackTraces = true
        exceptionFormat = TestExceptionFormat.FULL
    }
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

task<Wrapper>("wrapper") {
    gradleVersion = "4.6"
    distributionType = Wrapper.DistributionType.ALL
}