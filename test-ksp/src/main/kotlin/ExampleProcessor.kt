import com.google.auto.service.AutoService
import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import java.io.OutputStreamWriter

@AutoService(SymbolProcessorProvider::class)
public class ExampleProcessorProvider : SymbolProcessorProvider {
    override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
        return ExampleProcessor(environment)
    }
}

public class ExampleProcessor(private val env: SymbolProcessorEnvironment) : SymbolProcessor {
    private var invoked = false

    override fun process(resolver: Resolver): List<KSAnnotated> {

        if (invoked) {
            return emptyList()
        }
        invoked = true

        val functions = resolver.getSymbolsWithAnnotation("de.jensklingenberg.kspexample.ExampleAnnotation")
            .filterIsInstance<KSFunctionDeclaration>()

      val functionSource =  functions.joinToString(separator = "\n") { function ->
            """fun generatedFunction() {
                    println("Hello from ${function.simpleName.asString()}!")
                }"""
        }
            env.codeGenerator.createNewFile(
                dependencies = Dependencies(false, *functions.map { it.containingFile!! }.toList().toTypedArray()),
                packageName = "de.jensklingenberg.kspexample",
                fileName = "GeneratedClass"
            ).use { output ->
                OutputStreamWriter(output, Charsets.UTF_8).use { writer ->
                    writer.write(
                        """
                package de.jensklingenberg.kspexample
                $functionSource
            """
                    )
                }
            }





        return emptyList()
    }


}


