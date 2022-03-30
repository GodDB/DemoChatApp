import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":shared"))

    with(Libs.Kotlin) {
        implementation(kotlin)
        implementation(coroutine)
        implementation(coroutineAndroid)
    }

    with(Libs.Android) {
        implementation(core)
        implementation(appCompat)
        implementation(material)
        implementation(constraintLayout)
        implementation(lifecycle)
        implementation(recyclerView)
    }

    with(Libs.Android.JetPack) {
        implementation(viewModel)
        implementation(liveData)

        implementation(hilt_android)
        kapt(hilt_compiler)

        implementation(activityKtx)
        implementation(fragmentKtx)
    }

    with(Libs.Test) {
        testImplementation(kotlinTest)
        testImplementation(kotlinJUnit)
        testImplementation(coroutineTest)
        testImplementation(androidTest)
    }
}