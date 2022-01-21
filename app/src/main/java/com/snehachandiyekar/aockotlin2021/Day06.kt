package com.snehachandiyekar.aockotlin2021

fun main() {
    val input = readInputString("Day06.txt")
    val dayCountList = input[0].split(",").map { it.toInt() }
    part1Memo(dayCountList, 0)
}

val noOfDays = 256
var count = 0L
var fishMap = emptyMap<Int, Long>().toMutableMap()

fun part1Memo(input: List<Int>, state: Int): Long {
    for (i in input) {
        count++
        if (!fishMap.containsKey(i)) {
            var tempCount = getChildFishCount(state + i)
            fishMap[i] = tempCount
        }
        count += fishMap[i]!!
    }

    println("$count, ${fishMap.size}")
    return count
}

fun getChildFishCount(state: Int): Long {
    var runningState = state
    var tempCount = 0L
    while (runningState < noOfDays) {
        tempCount++
        println("$runningState $tempCount")
        tempCount += getChildFishCount(runningState + 9)
        runningState += 7
    }

    return tempCount
}
