plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.diegaspar.core_base"
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "11"
        }
    }
}

dependencies {

    implementation(Dependencies.androidCoreKtx)
    implementation(platform(Dependencies.kotlinBom))
    implementation(Dependencies.lifeCycleRuntime)
    implementation(Dependencies.koin)
    implementation(Dependencies.koinCompose)
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitConverterGson)
    implementation(Dependencies.coroutines)
    implementation(Dependencies.room)
    implementation(Dependencies.roomKtx)
    kapt(Dependencies.roomAnnotationProcessor)

    testImplementation(Dependencies.jUnit)
    testImplementation(Dependencies.mockitoCore)
    testImplementation(Dependencies.mockitoKotlin)
    testImplementation(Dependencies.mockitoInline)
    testImplementation(Dependencies.coroutinesTest)
    testImplementation(Dependencies.arch)
}