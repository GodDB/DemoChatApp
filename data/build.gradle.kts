plugins {
    id("com.android.library")
    id("kotlin-android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":shared"))

    with(Libs.Android.JetPack) {
        implementation(hilt_android)
        kapt(hilt_compiler)
    }

    with(Libs.Network) {
        implementation(retrofit)
        implementation(gson)
        implementation(okhttp)
        implementation(interceptor)
    }

    with(Libs.Test) {
        testImplementation(kotlinTest)
        testImplementation(kotlinJUnit)
        testImplementation(coroutineTest)
        testImplementation(androidTest)
    }
}