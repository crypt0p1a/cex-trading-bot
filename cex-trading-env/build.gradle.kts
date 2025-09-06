plugins {
    alias(additionals.plugins.kotlin.multiplatform)
    alias(additionals.plugins.kotlin.serialization)
    id("jvmCompat")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(additionals.kotlinx.serialization.json)
                api(additionals.multiplatform.file.access)
                implementation(additionals.multiplatform.sentry)
            }
        }

        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
                api(additionals.kotlinx.coroutines.test)
            }
        }
    }
}
