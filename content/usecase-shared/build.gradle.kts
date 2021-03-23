plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

android {
    compileSdkVersion(29)
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(29)
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}


kotlin {
    android()

    val onPhone = System.getenv("SDK_NAME")?.startsWith("iphoneos") ?: false
    if (onPhone) {
        iosArm64("ios")
    } else {
        iosX64("ios")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":content:domain-shared"))
                implementation(kotlin("stdlib-common", "1.4.31"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
            }
        }
    }

    version = "0.1"

    cocoapods {
        // Configure fields required by CocoaPods.
        summary = "Content Use Case shared library"
        homepage = "https://github.com/touchlab/KaMPKit"

        // You can change the name of the produced framework.
        // By default, it is the name of the Gradle project.
        frameworkName = "cleanarchitecture-content-usecase"

        ios.deploymentTarget = "13.5"
        podfile = project.file("../../ios/Podfile")
    }
}
