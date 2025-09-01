plugins {
    alias(additionals.plugins.kotlin.multiplatform)
    alias(additionals.plugins.kotlin.serialization)
    id("jvmCompat")
}

kotlin {
    jvm()

    sourceSets {
        val commonMain by getting

        val commonTest by getting {
            dependencies {
                api(kotlin("test"))
            }
        }

        val jvmMain by getting {
            dependencies {
                api(libs.ta4j)
            }
        }
    }
}
