package com.snehachandiyekar.aockotlin2021

fun main() {
    val input = readInputString("Day02.txt")

    fun part1(input: List<String>): Int {
        var horizontalPos = 0
        var depth = 0

        for (i in input.indices) {
            val command = input[i].split(" ")
            when (command[0]) {
                "forward" ->
                    horizontalPos += command[1].toInt()
                "down" ->
                    depth += command[1].toInt()
                "up" ->
                    depth -= command[1].toInt()
            }
        }
        return horizontalPos * depth
    }

    fun part2(input: List<String>): Int {
        var horizontalPos = 0
        var depth = 0
        var aim = 0

        for (i in input.indices) {
            val command = input[i].split(" ")
            when (command[0]) {
                "forward" -> {
                    horizontalPos += command[1].toInt()
                    depth += aim * command[1].toInt()
                }
                "down" ->
                    aim += command[1].toInt()
                "up" ->
                    aim -= command[1].toInt()
            }
        }
        println("$horizontalPos $depth")
        return horizontalPos * depth
    }

    println(part1(input))
    print(part2(input))
}

