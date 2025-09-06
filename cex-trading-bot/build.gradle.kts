import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    alias(additionals.plugins.kotlin.jvm)
    alias(additionals.plugins.kotlin.serialization)
    id("jvmCompat")
}

val mainClassInManifest = "eu.codlab.cex.ApplicationKt"

application {
    mainClass.set(mainClassInManifest)
}

dependencies {
    api(additionals.kotlinx.coroutines)
    api(additionals.kotlinx.coroutines.core)
    api(additionals.kotlinx.coroutines.jvm)

    api(additionals.kotlinx.serialization.json)

    api(additionals.multiplatform.file.access)

    testApi(kotlin("test"))

    api(additionals.kotlinx.coroutines.jvm)

    api(additionals.multiplatform.platform)

    api(libs.cex.spot.trading)
    api(libs.bignum)

    api(project(":cex-trading-env"))
    api(project(":cex-trading-database"))
    api(project(":cex-trading-tools"))
}
