plugins {
            alias(libs.plugins.androidApplication)
            id("com.google.gms.google-services")
        }

        android {
            namespace = "com.example.firstapp"
            compileSdk = 34

            defaultConfig {
                applicationId = "com.example.firstapp"
                minSdk = 24
                targetSdk = 34
                versionCode = 1
                versionName = "1.0"

                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        ext {
            var lottieVersion = "4.0.0"
        }

        dependencies {

            implementation(libs.appcompat)
            implementation(libs.material)
            implementation(libs.activity)
            implementation(libs.constraintlayout)
            implementation(libs.firebase.database)
            implementation(libs.firebase.auth)
            implementation(libs.cardview)
            implementation(libs.recyclerview)
            testImplementation(libs.junit)
            androidTestImplementation(libs.ext.junit)
            androidTestImplementation(libs.espresso.core)
            implementation("com.airbnb.android:lottie:4.0.0")
            implementation("com.google.firebase:firebase-dynamic-module-support:16.0.0-beta03")
            implementation("com.google.gms:google-services:4.4.1")
            implementation(platform("com.google.firebase:firebase-bom:33.0.0"))
            implementation("com.google.android.gms:play-services-auth:21.1.1")
            implementation("com.google.firebase:firebase-auth:23.0.0")
            implementation ("com.google.firebase:firebase-firestore:24.0.3")
            implementation ("androidx.recyclerview:recyclerview:1.2.1")
                implementation ("com.google.android.material:material:1.4.0")
                implementation ("com.google.android.material:material:1.5.0")


        }





        


