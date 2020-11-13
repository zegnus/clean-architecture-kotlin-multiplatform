object Versions {
    val androidMinSdk = 21
    val androidTargetSdk = 29
    val androidCompileSdk = 29
    val coroutines = "1.4.1"
}

object Dependencies {
    object Coroutines {
        val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }
}
