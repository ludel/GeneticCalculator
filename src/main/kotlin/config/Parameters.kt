package config

import beans.Equation

object Parameters {
    private val expression = { (x, y, z): IntArray -> x + (y - 50) / 20 - z }
    private const val result = 20
    private const val numberUnknowns = 3

    val equation: Equation = Equation(expression, result)

    var crossoverRate = 0.6
    var mutationRate = 0.3

    const val genesNb = numberUnknowns
    const val generationMaxNb = 30 * genesNb
    const val individualsNb = 10 * genesNb
    const val fitness = 0
}

