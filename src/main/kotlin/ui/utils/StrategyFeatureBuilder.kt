package io.github.smfdrummer.medal_app_desktop.ui.utils

import io.github.smfdrummer.utils.strategy.StrategyConfig
import io.github.smfdrummer.utils.strategy.StrategyContext

data class StrategyFeature(
    val title: String,
    val description: String,
    val inputs: List<FeatureInput>,
    val strategyBuilder: (Map<String, String>) -> StrategyConfig,
    val cutoffBuilder: ((Map<String, String>) -> ((Int) -> Boolean)?)? = null,
    val analyzeBuilder: ((StrategyContext, User) -> Unit)? = null
)

sealed interface FeatureInput {
    val key: String
    val label: String
    val isRequired: Boolean
    val validator: (String) -> Boolean
}

data class TextInput(
    override val key: String,
    override val label: String,
    override val isRequired: Boolean = true,
    override val validator: (String) -> Boolean = { it.isNotEmpty() }
) : FeatureInput

data class NumberInput(
    override val key: String,
    override val label: String,
    override val isRequired: Boolean = true,
    override val validator: (String) -> Boolean = { it.toIntOrNull() != null }
) : FeatureInput

class FeatureBuilder {
    private var title: String = ""
    private var description: String = ""
    private val inputs = mutableListOf<FeatureInput>()
    private var strategyBuilder: ((Map<String, String>) -> StrategyConfig)? = null
    private var cutoffBuilder: ((Map<String, String>) -> ((Int) -> Boolean)?)? = null
    private var analyzeBuilder: ((StrategyContext, User) -> Unit)? = null

    fun title(block: () -> String) {
        title = block()
    }

    fun description(block: () -> String) {
        description = block()
    }

    fun inputs(block: InputsBuilder.() -> Unit) {
        val builder = InputsBuilder()
        builder.block()
        inputs.addAll(builder.build())
    }

    fun strategy(block: (Map<String, String>) -> StrategyConfig) {
        strategyBuilder = block
    }

    fun cutoff(block: (Map<String, String>) -> ((Int) -> Boolean)?) {
        cutoffBuilder = block
    }

    fun analyze(block: (StrategyContext, User) -> Unit) {
        analyzeBuilder = block
    }

    fun build(): StrategyFeature {
        require(strategyBuilder != null) { "Strategy builder must be specified" }
        return StrategyFeature(
            title = title,
            description = description,
            inputs = inputs,
            strategyBuilder = strategyBuilder!!,
            cutoffBuilder = cutoffBuilder,
            analyzeBuilder = analyzeBuilder
        )
    }
}

class InputsBuilder {
    private val inputs = mutableListOf<FeatureInput>()

    fun text(key: String, block: () -> String) {
        inputs.add(TextInput(key = key, label = block()))
    }

    fun number(key: String, block: () -> String) {
        inputs.add(NumberInput(key = key, label = block()))
    }

    fun build(): List<FeatureInput> = inputs
}

class FeaturesBuilder {
    private val features = mutableListOf<StrategyFeature>()

    fun feature(block: FeatureBuilder.() -> Unit) {
        val builder = FeatureBuilder()
        builder.block()
        features.add(builder.build())
    }

    fun build(): List<StrategyFeature> = features
}

fun buildFeatures(block: FeaturesBuilder.() -> Unit): List<StrategyFeature> {
    val builder = FeaturesBuilder()
    builder.block()
    return builder.build()
}