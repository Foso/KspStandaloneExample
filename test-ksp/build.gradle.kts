import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm")
    kotlin("kapt")
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
    implementation(libs.kspApi)
    implementation("com.google.devtools.ksp:symbol-processing-aa:2.0.0-Beta1-1.0.15")
    implementation("com.google.devtools.ksp:symbol-processing-common-deps:2.0.0-Beta1-1.0.15")
    implementation("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.0.0-Beta1")
    compileOnly(libs.autoService)
    kapt(libs.autoService)


}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask<*>>().configureEach {
    compilerOptions.freeCompilerArgs.add("-opt-in=org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi")
}


