plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.assertj:assertj-core:3.26.3")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
}

tasks.register<Test>("runSmokeTest") {
    val testTask = tasks.test.get()
    testClassesDirs = testTask.testClassesDirs
    classpath = testTask.classpath
    useJUnitPlatform() {
        includeTags("Smoke")
    }

    testLogging {
        events("passed", "skipped", "failed")
    }
    outputs.upToDateWhen { false }
}

tasks.register<Test>("runPackage"){
    val testTask = tasks.test.get()
    testClassesDirs = testTask.testClassesDirs
    classpath = testTask.classpath
    filter.includeTestsMatching("homework.task2.*")
    useJUnitPlatform(){
    }
    testLogging {
        events("passed", "skipped", "failed")
    }
    outputs.upToDateWhen { false }
}

tasks.register<Test>("runPackageAndTags"){
    val testTask = tasks.test.get()
    testClassesDirs = testTask.testClassesDirs
    classpath = testTask.classpath
    useJUnitPlatform()
    filter.includeTestsMatching("homework.task2.*")
    filter.includeTestsMatching("Task2")
    testLogging {
        events("passed", "skipped", "failed")
    }
    outputs.upToDateWhen { false }
}