plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.fitgoalsappsmoviles.fitgoals"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.fitgoalsappsmoviles.fitgoals"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Import the Firebase BoM
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))

    // TODO: Add the dependencies for Firebase products you want to use
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation("com.google.firebase:firebase-analytics")

    // Firebase Authentication
    implementation(platform("com.google.firebase:firebase-auth-ktx"))
    // Cloud Firestore
    implementation(platform("com.google.firebase:firebase-firestore-ktx"))
    //Cloud Functions para Firebase
    implementation(platform("com.google.firebase:firebase-functions-ktx"))

    //Firebase installs
    implementation(platform("com.google.firebase:firebase-installations-ktx"))

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation(platform("com.google.firebase:firebase-auth"))

    // Also add the dependency for the Google Play services library and specify its version
    implementation(platform("com.google.android.gms:play-services-auth:21.0.0"))

}