plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "com.technicaltest.network"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        debug {
            buildConfigField(
                "String",
                "BASE_URL",
                "\"https://api.openweathermap.org\""
            )

            buildConfigField(
                "String",
                "WEATHER_API_KEY",
                System.getenv("WEATHER_API_KEY") ?: "\"1f6ca21456731b9de44ce0ea03c478d0\""
            )
            buildConfigField(
                "String",
                "APPLICATION_ID",
                System.getenv()["APPLICATION_ID"] ?: "\"6a2NWTwXRlwc1BynCf46kYZG1VeWp170GYjZIeXK\""
            )
            buildConfigField(
                "String",
                "MASTER_KEY",
                System.getenv()["MASTER_KEY"] ?: "\"WEYdiGWSz0gt91skfDe03wX9yqikQTpiVc9Vn2An\""
            )
        }
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_9
        targetCompatibility = JavaVersion.VERSION_1_9
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_9.toString()
    }

    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(libs.okhttp)
    api(libs.retrofit)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.logging.interceptor)
    api(libs.gson)

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}