import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import java.io.FileInputStream
import java.util.*

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

    defaultConfig {
        val localPropertiesFile = rootProject.file("local.properties")
        val apikeyProperties = Properties()
        apikeyProperties.load(FileInputStream(localPropertiesFile))
        buildConfigField("String", "oauth_web_key", (apikeyProperties["OAUTH_WEB_KEY"] as? String) ?: "Insert your Api Key")
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

    with(Libs.Firebase) {
        implementation(platform(bom))
        implementation(auth)
        implementation(playService)
    }
}