plugins {
    id("com.android.library")
    kotlin("android")
}

dependencies {

    with(Libs.Kotlin) {
        implementation(kotlin)
    }

    implementation(Libs.inject)

    with(Libs.Test) {
        testImplementation(kotlinTest)
        testImplementation(kotlinJUnit)
        testImplementation(coroutineTest)
        testImplementation(androidTest)
    }
}