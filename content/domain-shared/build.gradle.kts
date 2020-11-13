plugins {
    kotlin("multiplatform")
    id("co.touchlab.native.cocoapods")
    id("com.android.library")
}

android {
    compileSdkVersion(Versions.androidCompileSdk)
    defaultConfig {
        minSdkVersion(Versions.androidMinSdk)
        targetSdkVersion(Versions.androidTargetSdk)
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
                implementation(Dependencies.Coroutines.common)
            }
        }

        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Coroutines.android)
            }
        }

        val iosMain by getting {
            dependencies {
                implementation(Dependencies.Coroutines.common)
            }
        }
    }

    cocoapodsext {
        summary = "Content Domain shared library"
        homepage = "https://github.com/touchlab/KaMPKit"
        framework {
            isStatic = false
            transitiveExport = true
        }
    }
}


