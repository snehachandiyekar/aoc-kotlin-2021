package com.snehachandiyekar.aockotlin2021

fun main() {
    val input = readInput("Day01.txt")
    fun part1(input: List<Int>): Int {
        var count = 0
        for (i in 0 until input.size - 1) {
            if (input[i + 1] > input[i])
                count++

        }
        return count
    }

    fun part2(input: List<Int>): Int {
        var count = 0
        for (i in 0 until input.size - 3) {
            if (input[i + 3] > input[i])
                count++

        }
        return count
    }

    println(part1(input))
    print(part2(input))
}


