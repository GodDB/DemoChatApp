plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
}

android {
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(project(":feature"))
    implementation(project(":domain"))
    implementation(project(":shared"))
    implementation(project(":data"))

    with(Libs.Kotlin) {
        implementation(kotlin)
        implementation(coroutine)
    }

    with(Libs.Android) {
        implementation(appCompat)
        implementation(core)
        implementation(appCompat)
        implementation(material)
        implementation(constraintLayout)
    }

    with(Libs.Android.JetPack) {
        implementation(hilt_android)
        kapt(hilt_compiler)
        implementation(activityKtx)
        implementation(fragmentKtx)
    }

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

    with(Libs.Firebase) {
        implementation(platform(bom))
        implementation(analytics)
    }
}
