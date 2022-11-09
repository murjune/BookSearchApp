import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
    id("org.jetbrains.kotlin.plugin.serialization")
}

val properties = Properties()
properties.load(project.rootProject.file("local.properties").inputStream())

android {
    compileSdk = 32

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdkVersion
        targetSdk = AppConfig.targetSdkVersion
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName
        buildConfigField("String", "BASE_URL", properties.getProperty("BASE_URL"))
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    val hilt_version = "2.43.2"
    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")
    // timber
    implementation("com.jakewharton.timber:timber:5.0.1")
    // Hilt
    implementation("com.google.dagger:hilt-android:$hilt_version")
    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
    kapt("com.google.dagger:hilt-compiler:$hilt_version")
    // kotlinx-serialization-json
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    // room
    val roomVersion = "2.4.2"
    implementation("androidx.room:room-ktx:$roomVersion")
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
    // coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.2")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.2")
    // navigation
    val nav_version = "2.5.0"
    implementation("androidx.navigation:navigation-ui-ktx:2.5.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.0")
    // retrofit2
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // retrofit2에서 gson 사용을 위한 컨버터
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // gson
    implementation("com.google.code.gson:gson:2.9.0")
    // coil
    implementation("io.coil-kt:coil:2.2.2")
    // fragment ktx
    implementation("androidx.core:core-ktx:1.8.0")
    // material
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    // activity-ktx
    implementation("androidx.activity:activity-ktx:1.5.0")
    // fragment-ktx
    implementation("androidx.fragment:fragment-ktx:1.5.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0")
    // ViewModel utilities for Compose
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.5.0")
    // viewModel savedstate
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.0")
    // LiveData -옵저버 패턴 관련 - 즉 데이터의 변경사항을 알 수 있다.
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.0")
    // retropit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Gson converter
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    // Gson
    implementation("com.google.code.gson:gson:2.9.0")
    // Reprofit logging Intercepter
    implementation("com.squareup.okhttp3:logging-interceptor:4.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.appcompat:appcompat:1.4.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
