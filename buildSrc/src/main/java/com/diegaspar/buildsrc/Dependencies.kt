object Dependencies {
    val androidCoreKtx by lazy { "androidx.core:core-ktx:${Versions.coreKtx}" }
    val kotlinBom by lazy { "org.jetbrains.kotlin:kotlin-bom:${Versions.kotlinBom}" }
    val lifeCycleRuntime by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifeCycleRuntime}" }
    val activityCompose by lazy { "androidx.activity:activity-compose:${Versions.activityCompose}" }
    val viewModelCompose by lazy { "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.viewModelCompose}" }
    val composeBom by lazy { "androidx.compose:compose-bom:${Versions.composeBom}" }
    val composeUI by lazy { "androidx.compose.ui:ui:${Versions.compose}" }
    val composeUIGraphics by lazy { "androidx.compose.ui:ui-graphics:${Versions.compose}" }
    val composeUIToolingPreview by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.compose}" }
    val composeMaterial3 by lazy { "androidx.compose.material3:material3:${Versions.composeMaterial3}" }
    val composeTooling by lazy { "androidx.compose.ui:ui-tooling:${Versions.compose}" }
    val composeTestManifest by lazy { "androidx.compose.ui:ui-test-manifest:${Versions.compose}" }
    val composeContraintLayout by lazy { "androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayoutCompose}" }
    val lottieCompose by lazy { "com.airbnb.android:lottie-compose:${Versions.lottie}" }
    val coilCompose by lazy { "io.coil-kt:coil-compose:${Versions.coil}" }
    val palette by lazy { "androidx.palette:palette:${Versions.palette}" }

    val koin by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    val koinCompose by lazy { "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}" }
    val room by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomAnnotationProcessor by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitConverterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}" }

    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
    val mockitoCore by lazy { "org.mockito:mockito-core:${Versions.mockitoCore}" }
    val mockitoKotlin by lazy { "org.mockito.kotlin:mockito-kotlin:${Versions.mockitoKotlin}" }
    val mockitoInline by lazy { "org.mockito:mockito-inline:${Versions.mockitoInline}" }
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}" }
    val arch by lazy { "androidx.arch.core:core-testing:${Versions.arch}" }
}