package engine

import entities.Gene
import entities.Individual
import entities.Population
import config.Parameters


class Factory(private val config: Parameters) {
    private var population: Population = Population(config.equation)
    var bestIndividual: Individual = Individual()
    private var generationCursor: Int = 0

    init {
        println(config.equation.expression)
        for (i in 0..config.individualsNb) {
            val individual = Individual()

            for (g in 1..config.genesNb) {
                individual.genotype.add(Gene())
            }

            population.individuals.add(individual)
        }
    }

    private fun selection(index: Int): Individual {
        return population.individuals[index]
    }


    private fun crossover(father: Individual, mother: Individual): Individual {
        val child = Individual()

        for (i in 0 until config.genesNb) {
            val geneValue = (father.genotype[i].value + mother.genotype[i].value) / 2
            child.genotype.add(Gene(geneValue))
        }

        return child
    }

    private fun mutation(child: Individual): Individual {
        val newChild = Individual()

        for (gene in child.genotype) {
            var binary = gene.value.toString(2)

            val startIndex = if (binary.startsWith("-")) 1 else 0
            val randomIndex = (startIndex until binary.length).random()

            val replacement = if (binary[randomIndex].equals("1")) "0" else "0"
            binary = binary.replaceRange(randomIndex, randomIndex, replacement)
            newChild.genotype.add(Gene(binary.toInt(2)))
        }

        return newChild
    }


    fun run() {
        while (bestIndividual.fitness > config.fitness && generationCursor < config.generationMaxNb) {
            population.calculateFitness()
            population.sortByFitness()
            bestIndividual = population.fittest

            val newGeneration = Population(config.equation)
            newGeneration.individuals.add(bestIndividual)

            for (i in 1 until config.individualsNb) {
                val father = selection(i - 1)
                val mother = selection(i)

                var child = crossover(father, mother)

                if ((0..100).random() < config.mutationRate * 100) {
                    child = mutation(child)
                }

                newGeneration.individuals.add(child)
            }

            println("Generation: $generationCursor - Fitness: ${bestIndividual.fitness} - Values: ${bestIndividual.getGenotypeValues()}")

            generationCursor++
            population = newGeneration
        }
    }

    fun summary() {
        println("\n-=-=-=- Summary -=-=-=-")
        println("solved=${bestIndividual.fitness == 0}")
        println("generation=$generationCursor")
        println("genesValues=${bestIndividual.getGenotypeValues()}")
        println("bestFitness=${bestIndividual.fitness}")
        println(bestIndividual.getGenotypeValues()[0].toString(2))
    }
}