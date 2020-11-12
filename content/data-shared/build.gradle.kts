plugins {
    kotlin("multiplatform")
    id("co.touchlab.native.cocoapods")
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
                implementation(kotlin("stdlib-common", "1.4.10"))
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
            }
        }
    }

    cocoapodsext {
        summary = "Content Data shared library"
        homepage = "https://github.com/touchlab/KaMPKit"
        framework {
            isStatic = false
            transitiveExport = true
        }
    }
}
