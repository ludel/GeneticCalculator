package entities

import beans.Equation


class Population(private val equation: Equation) {
    var individuals: MutableList<Individual> = mutableListOf()
    lateinit var fittest: Individual

    fun calculateFitness() {
        individuals.map { it.setFitness(equation) }
    }

    fun sortByFitness() {
        individuals.sortBy { it.fitness }
        fittest = individuals[0]
    }

}