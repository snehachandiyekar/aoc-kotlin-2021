package com.snehachandiyekar.aockotlin2021

import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    val input = readInputString("Day07.txt")
    val posList = input[0].split(",").map { it.toInt() }
    println(part1(posList))
    println(part2(posList))
}

fun part2(posList: List<Int>): Int {
    val med = (posList.sum().toDouble() / posList.size)
    val floor = floor(med).toInt()
    val ceil = ceil(med).toInt()
    return minOf(getHorizontalMoves(posList, floor), getHorizontalMoves(posList, ceil))

}

fun getHorizontalMoves(posList: List<Int>, mid: Int): Int {
    var total = 0
    for (item in posList) {
        val diff = Math.abs(item - mid)
        val sum = diff * (diff + 1) / 2
        total += sum
    }
    return total
}

fun part1(input: List<Int>): Int {
    val posList = input.sorted()
    val mid = posList[posList.size / 2]
    val sumOf = posList.sumOf { Math.abs(it - mid) }
    /*for (item in posList) {
        count += Math.abs(item - mid)
    }*/
    //println("$mid, $sumOf")
    return sumOf
}