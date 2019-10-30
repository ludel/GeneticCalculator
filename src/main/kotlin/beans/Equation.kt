package beans

import kotlin.math.absoluteValue

class Equation(val expression: (IntArray) -> Int, private val result: Int) {
    fun distance(testValue: IntArray): Int {
        return (expression(testValue) - result).absoluteValue
    }
}