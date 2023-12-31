@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin ("kapt")
    id ("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.movil"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.example.movil"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation("androidx.room:room-common:2.6.0")
   //implementation("com.google.firebase:firebase-appdistribution-gradle:4.0.1")
    // Room
    val room_version = "2.6.0"
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    //  Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-compiler:2.46.1")

    //Navigation

    // Swipe
    implementation("me.saket.swipe:swipe:1.1.1")

    //pantallas
    implementation("androidx.compose.material3:material3-window-size-class")

    //imagenes
    implementation("io.coil-kt:coil-compose:2.3.0")
    //reconocimento de texto en imagenes
    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")


     //biblioteca piccaso para mostrar imagenes almacenadas en la memoria del telefono
    implementation ("com.squareup.picasso:picasso:2.71828")


    //animaciones lottiefiles
    implementation("com.airbnb.android:lottie-compose:5.2.0")



    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.0")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")




    dependencies {
        val nav_version = "2.5.3"

        implementation("androidx.navigation:navigation-compose:$nav_version")
        //para reproducui el video
       // implementation ("com.github.halilozercan:ComposeVideoPlayer:0.1.0")
    }

     // ExoPlayer
    implementation("com.google.android.exoplayer:exoplayer:2.15.1")


    //imagenes
    implementation("io.coil-kt:coil-compose:2.3.0")
    //reconocimento de texto en imagenes
    implementation("com.google.android.gms:play-services-mlkit-text-recognition:19.0.0")

}