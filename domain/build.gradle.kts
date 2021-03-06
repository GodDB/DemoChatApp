import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    implementation(project(":shared"))

    with(Libs.Kotlin) {
        implementation(kotlin)
        implementation(coroutine)
        implementation(coroutineAndroid)
    }

    with(Libs.Android.JetPack) {
        implementation(hilt_android)
        kapt(hilt_compiler)
    }

    with(Libs.Test) {
        testImplementation(kotlinTest)
        testImplementation(kotlinJUnit)
        testImplementation(coroutineTest)
        testImplementation(androidTest)
    }
}