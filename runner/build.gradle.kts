import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm")
    id("com.vanniktech.maven.publish")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    implementation(projects.testKsp)
    implementation("com.google.devtools.ksp:symbol-processing-aa:2.0.0-Beta1-1.0.15")
    implementation("com.google.devtools.ksp:symbol-processing-common-deps:2.0.0-Beta1-1.0.15")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.0.0-Beta1")

}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
    compilerOptions.freeCompilerArgs.add("-opt-in=org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
}

