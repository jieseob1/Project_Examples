package org.example

import kotlinx.coroutines.*
import org.example.KotlinBaseExample.ObjectClassDiff
import java.time.Duration
import java.time.LocalTime

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
suspend fun main() {
//    println(Duration.parse("PT3H"))

//    runBlocking { // 이렇게 하면 기다렸다가 하게 됨 main이 종료되지 않고 코루틴의 실행이 끝날때까지 기다린다.
//        coroutineScope {
//            launch {
//                val networkData = getNetowrkData()
//                val dbData = getDBData()
//                println("Data is $networkData and $dbData")
//            }
//
//        }
//    }

//    println("Hello, World!")
    val objectClassDiff = ObjectClassDiff()
    println(objectClassDiff.testObject())
    //실행 안되는 이유 => 메인 스레드가 종료되고 나서 실행됨
}

private suspend fun getNetowrkData(): String {
    delay(1000L)
    return "Network Data"
}
private suspend fun getDBData(): String {
    delay(1000L)
    return "DB Data"
}
