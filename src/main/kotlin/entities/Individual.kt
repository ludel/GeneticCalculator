package entities

import beans.Equation

open class Individual(var genotype: MutableList<Gene> = mutableListOf()) {
    var fitness: Int = 1

    fun setFitness(equation: Equation) {
        fitness = equation.distance(getGenotypeValues().toIntArray())
    }

    fun getGenotypeValues(): List<Int> {
        return genotype.map { it.value }
    }
}