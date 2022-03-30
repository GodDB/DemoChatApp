object Libs {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"

    object Kotlin {
        const val plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.Kotlin.kotlin}"
        const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.Kotlin.kotlin}"
        const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Test.coroutineAndroid}"
    }

    object Test {
        const val kotlinTest = "org.jetbrains.kotlin:kotlin-test:${Versions.Kotlin.kotlin}"
        const val kotlinJUnit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.Kotlin.kotlin}"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Kotlin.coroutineTest}"
        const val androidTest = "androidx.arch.core:core-testing:${Versions.Test.androidTest}"
    }

    object Android {
        const val core = "androidx.core:core-ktx:${Versions.Android.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Android.appCompat}"
        const val material = "com.google.android.material:material:${Versions.Android.material}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Android.constraintLayout}"
        const val lifecycle = "androidx.lifecycle:lifecycle-extensions:${Versions.Android.lifecycle}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.Android.recyclerView}"


        object JetPack {
            const val hilt_plugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.Android.hilt}"
            const val hilt_android = "com.google.dagger:hilt-android:${Versions.Android.hilt}"
            const val hilt_compiler = "com.google.dagger:hilt-android-compiler:${Versions.Android.hilt}"

            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Android.viewModel}"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Android.liveData}"
            const val activityKtx = "androidx.activity:activity-ktx:${Versions.Android.activityKtx}"
            const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Android.fragmentKtx}"
        }
    }

    object Network {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Network.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.Network.retrofit}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Network.okHttp}"
        const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.Network.okHttp}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.Glide.glide}"
        const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.Glide.glide_compiler}"
    }

    const val inject = "javax.inject:javax.inject:1"
}