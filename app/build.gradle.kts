plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.mockinvestor"
    compileSdk = 34



    defaultConfig {
        applicationId = "com.example.mockinvestor"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


    }
    buildFeatures {
        buildConfig = true
    }

    buildTypes {
        release {

            buildConfigField("String", "ALPHA_VANTAGE_API_KEY", "\"${project.findProperty("ALPHA_VANTAGE_API_KEY") ?: System.getenv("ALPHA_VANTAGE_API_KEY")}\"")
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
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.navigation:navigation-fragment:2.7.5")
    implementation("androidx.navigation:navigation-ui:2.7.5")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.opencsv:opencsv:4.6")


    //implementation ("org.openjdk.jdk.incubator:httpclient:2.0.9");

    //implementation("com.google.code.gson:gson:2.10.1")
    //implementation("com.squareup.okhttp3:okhttp:4.9.1")
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
}