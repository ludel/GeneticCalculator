package engine

import config.Parameters

fun main() {
    val factory = Factory(Parameters)

    factory.run()
    factory.summary()
}

