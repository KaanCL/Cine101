import com.android.build.api.dsl.ViewBinding

plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.cine101"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.cine101"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    viewBinding{
      enable=true

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.squareup.retrofit2:retrofit:2.3.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.3.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.3.0")
    implementation ("androidx.cardview:cardview:1.0.0")
    implementation ("io.reactivex.rxjava2:rxjava:2.1.1")
    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("com.squareup.picasso:picasso:2.71828")

    implementation("com.github.bumptech.glide:glide:4.12.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")



}