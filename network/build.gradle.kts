plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    namespace = "com.technicalTest.api"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        buildConfigField("String", "WEATHER_API_KEY", "\"${System.getenv("WEATHER_API_KEY")}\"")
        buildConfigField("String", "APPLICATION_ID", "\"${System.getenv()["APPLICATION_ID"]}\"")
        buildConfigField("String", "MASTER_KEY", "\"${System.getenv()["MASTER_KEY"]}\"")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {

    /** RETROFIT **/
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.logging.interceptor)
    implementation(libs.okhttp)

    /** HILT **/
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    /** UNIT TEST**/
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}