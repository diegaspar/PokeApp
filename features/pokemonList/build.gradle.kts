plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    namespace = "com.diegaspar.pokemonlist"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

dependencies {

    implementation(project(":core-base"))
    implementation(project(":core-ui"))

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

    debugImplementation(Dependencies.composeTooling)
    debugImplementation(Dependencies.composeTestManifest)

    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.mockitoInline)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.arch)
}