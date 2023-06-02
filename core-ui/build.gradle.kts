plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.diegaspar.core_ui"
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        minSdk = Config.minSdkVersion

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
}

dependencies {

    implementation(project(":core-base"))

    implementation(Dependencies.androidCoreKtx)
    implementation(platform(Dependencies.kotlinBom))
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.activityCompose)
    implementation(Dependencies.viewModelCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUI)
    implementation(Dependencies.composeUIGraphics)
    implementation(Dependencies.composeUIToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.composeContraintLayout)
    implementation(Dependencies.koin)
    implementation(Dependencies.koinCompose)
    implementation(Dependencies.lottieCompose)
    testImplementation(Dependencies.jUnit)
    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.composeTestManifest)
}