plugins {
    id("org.jetbrains.intellij") version Versions.intellijPlugin
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":hh-plugins-core"))
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("reflect"))
    implementation(Libs.freemarker)

    testImplementation(Libs.tests.kotest) // for kotest framework
}

// region Setup gradle-intellij-plugin
val currentVersion = Versions.chosenProduct

intellij {
    type = "IC"
    if (currentVersion.isLocal) {
        localPath = currentVersion.ideVersion
    } else {
        version = currentVersion.ideVersion
    }
    setPlugins("java", "Kotlin", "android")
}
// endregion

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("passed", "skipped", "failed")
    }
}
