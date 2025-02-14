package org.example

class SequenceTest

private var list: List<Int> = mutableListOf()

fun main() {
    makeList(list)
    withoutSequence()
    withSequence()
}

private fun withoutSequence() {
    println("withoutSequence")
    template {
        list.filter { it % 2 == 0 }
    }
}

private fun withSequence() {
    println("withSequence")
    template {
        list.asSequence()
            .filter { it % 2 == 0 }
    }
}

private fun makeList(list: List<Int>): Unit {
    for (i in 1..10000000) {
        list.plus(i)
    }
}

private fun template(block: () -> Unit) {
    val start = System.currentTimeMillis()
    block()
    val end = System.currentTimeMillis()
    println("Time: ${end - start}")

}

