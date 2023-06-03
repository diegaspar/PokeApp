plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = Config.nameSpace
    compileSdk = Config.compileSdkVersion

    defaultConfig {
        applicationId = Config.applicationId
        minSdk = Config.minSdkVersion
        targetSdk = Config.targetSdkVersion
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

}

dependencies {

    implementation(project(":core-base"))
    implementation(project(":features:pokemonList"))
    implementation(project(":features:pokemonDetail"))

    implementation(Dependencies.androidCoreKtx)
    implementation(platform(Dependencies.kotlinBom))
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.activityCompose)
    implementation(platform(Dependencies.composeBom))
    implementation(Dependencies.composeUI)
    implementation(Dependencies.composeUIGraphics)
    implementation(Dependencies.composeUIToolingPreview)
    implementation(Dependencies.composeMaterial3)
    implementation(Dependencies.koin)
    implementation(Dependencies.koinCompose)
    implementation(Dependencies.navigationCompose)
    testImplementation(Dependencies.jUnit)
    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.composeTestManifest)
}