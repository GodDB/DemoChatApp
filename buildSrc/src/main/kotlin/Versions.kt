object App {
    const val compileSdk = 30
    const val minSdk = 21
    const val targetSdk = 30
    const val versionCode = 1
    const val versionName = "1.0.0"

    const val appId = "com.example.demochatapp"
    const val buildToolsVersion = "30.0.3"
}

object Versions {

    const val gradle = "7.0.2"

    object Kotlin {
        const val kotlin = "1.6.0"
        const val coroutineTest = "1.6.0"
    }

    object Test{
        const val androidTest = "2.1.0"
        const val coroutineAndroid = "1.3.9"
    }

    object Android {
        const val core = "1.6.0"
        const val appCompat = "1.3.0"
        const val material = "1.4.0"
        const val constraintLayout = "2.0.4"
        const val lifecycle = "2.2.0"
        const val activityKtx = "1.2.4"
        const val fragmentKtx = "1.2.4"
        const val recyclerView = "1.2.0"

        const val viewModel = "2.3.1"
        const val liveData = "2.3.1"
        const val hilt = "2.37"
        const val viewModel_hilt = "1.0.0"
    }

    object Network {
        const val retrofit = "2.9.0"
        const val okHttp = "4.9.1"
    }

    object Glide {
        const val glide = "4.12.0"
        const val glide_compiler = "4.11.0"
    }

}