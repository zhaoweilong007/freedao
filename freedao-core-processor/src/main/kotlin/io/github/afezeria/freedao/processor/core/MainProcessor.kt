package io.github.afezeria.freedao.processor.core

import io.github.afezeria.freedao.annotation.Dao
import java.io.PrintWriter
import java.io.StringWriter
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.ProcessingEnvironment
import javax.annotation.processing.RoundEnvironment
import javax.lang.model.SourceVersion
import javax.lang.model.element.Element
import javax.lang.model.element.TypeElement
import javax.tools.Diagnostic
import kotlin.reflect.full.memberProperties
import kotlin.system.measureTimeMillis


/**
 *
 */
class MainProcessor : AbstractProcessor() {
    override fun getSupportedOptions(): MutableSet<String> {
        return mutableSetOf(
            *GlobalState::class.memberProperties.map {
                "freedao.${it.name}"
            }.toTypedArray()
        )
    }

    override fun getSupportedSourceVersion(): SourceVersion {
        return SourceVersion.RELEASE_8
    }

    override fun getSupportedAnnotationTypes(): MutableSet<String> {
        return mutableSetOf(Dao::class.qualifiedName!!)
    }

    private val elementCache = mutableSetOf<Element>()

    override fun process(
        annotations: MutableSet<out TypeElement>?,
        roundEnv: RoundEnvironment,
    ): Boolean {
        if (isLombokInvoked) {
            val elements = roundEnv.getElementsAnnotatedWith(Dao::class.java)
            if (elements.isNotEmpty() || elementCache.isNotEmpty()) {
                val time = measureTimeMillis {
                    elements.forEach {
                        processElement(it)
                    }
                    elementCache.removeAll {
                        processElement(it)
                        true
                    }
                }
                println("========= freedao execution time:${time}ms")
            }
        } else {
            elementCache.addAll(roundEnv.getElementsAnnotatedWith(Dao::class.java))
        }
        return false
    }

    override fun init(processingEnv: ProcessingEnvironment) {
        super.init(processingEnv)
        processingEnvironment = processingEnv
        GlobalState.init()
    }


    private fun processElement(element: Element) {
        try {
            runCatchingHandlerExceptionOrThrow(element) {
                DaoHandler(element as TypeElement).render()
            }
        } catch (e: Exception) {
            val stringWriter = StringWriter()
            val printWriter = PrintWriter(stringWriter)
            e.printStackTrace(printWriter)
            processingEnv.messager.printMessage(
                Diagnostic.Kind.ERROR,
                stringWriter.toString()
            )
        }
    }

    private val isLombokInvoked: Boolean
        get() = runCatching {
            Class.forName("lombok.launch.AnnotationProcessorHider\$AstModificationNotifierData")
                .getField("lombokInvoked")
                .getBoolean(null)
        }.getOrDefault(true)
}