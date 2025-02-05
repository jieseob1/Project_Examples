package org.example.coroutine

import kotlinx.coroutines.*

fun main() {
    runBlocking {

        coroutineScope {
            launch {
                val  asyncedDB = async { getDbTest() }
                val asyncedNetwork = async { getNetworkTest() }

                println(asyncedDB.await())
                println(asyncedNetwork.await())
            }
        }

    }

}

suspend fun getDbTest() {
    delay(1000L)
    println("getDbTest")
}

suspend fun getNetworkTest() {
    delay(1000L) // 코루틴이거나, suspend함수에서 호출해야됨
    println("getNetworkTest")
}

// withContext관련



