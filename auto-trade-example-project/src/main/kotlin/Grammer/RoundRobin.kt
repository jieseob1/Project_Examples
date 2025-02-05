package org.example.Grammer

import java.util.concurrent.atomic.AtomicInteger

class RoundRobin<T> (private val list: List<T>) {
    private val counter = AtomicInteger(0)

    fun getNext(): T {
        val index = counter.getAndUpdate{ (it +1) % list.size }
        return list[index]
    }
}

fun main() {
    val servers = listOf("A", "B", "C")
    val roundRobin = RoundRobin(servers) //RoundRobin<String>
    repeat(10) {
        println(roundRobin.getNext()) // a,b,c,a,b,c,a,b,c
    }
}
