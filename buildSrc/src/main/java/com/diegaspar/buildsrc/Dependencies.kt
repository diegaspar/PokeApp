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

    val koin by lazy { "io.insert-koin:koin-android:${Versions.koin}" }
    val koinCompose by lazy { "io.insert-koin:koin-androidx-compose:${Versions.koinCompose}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitConverterGson by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }

    val jUnit by lazy { "junit:junit:${Versions.jUnit}" }
}