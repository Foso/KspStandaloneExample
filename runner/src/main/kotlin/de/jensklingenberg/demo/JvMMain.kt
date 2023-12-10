package de.jensklingenberg.demo


import ExampleProcessorProvider
import com.google.devtools.ksp.impl.KotlinSymbolProcessing
import com.google.devtools.ksp.processing.KSPJvmConfig
import com.google.devtools.ksp.processing.KSPLogger
import com.google.devtools.ksp.symbol.KSNode
import java.io.File

fun main() {
    val basePath = File("").absolutePath+"/"
    val module = "sandbox"
    val kspConfig = KSPJvmConfig.Builder().apply {
        javaOutputDir = File("$basePath${module}/build/generated/source/ksp/java/main")
        jvmTarget = "1.8"
        moduleName = module
        commonSourceRoots = listOf(File("$basePath${module}/src/commonMain/kotlin"))
        sourceRoots = listOf(
            File("$basePath${module}/src/jvmMain/kotlin")
        )
        projectBaseDir = File(basePath + module)
        outputBaseDir = File("$basePath${module}/build/generated/source/ksp")
        cachesDir = File("$basePath${module}/build/generated/source/ksp/kspCaches")
        classOutputDir = File("$basePath${module}/build/generated/source/ksp/classes/main")
        kotlinOutputDir = File("$basePath${module}/build/generated/source/ksp/src/main/kotlin")
        resourceOutputDir = File("$basePath${module}/build/generated/source/ksp/src/main/resources")
        languageVersion = "1.9.21"
        apiVersion = "1.9.21"
        processorOptions =
            mapOf("abc" to "123")
        // All configurations happen here.
    }.build()
    val exitCode = KotlinSymbolProcessing(
        kspConfig,
        listOf(ExampleProcessorProvider()),
        object : KSPLogger {
            override fun error(message: String, symbol: KSNode?) {
                println("ERROR: $message")
            }

            override fun exception(e: Throwable) {

            }

            override fun info(message: String, symbol: KSNode?) {

            }

            override fun logging(message: String, symbol: KSNode?) {

            }

            override fun warn(message: String, symbol: KSNode?) {

            }

        }).execute()
    println(exitCode)
}

