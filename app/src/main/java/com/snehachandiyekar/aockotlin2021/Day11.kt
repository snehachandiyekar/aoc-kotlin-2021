package com.snehachandiyekar.aockotlin2021

fun main() {
    val input: List<String> = readInputString("Day11.txt")

    var matrix: Array<Array<OctopusVertex>> = Array(input.size) { row ->
        Array(input[0].length) { col ->
            OctopusVertex(input[row][col].digitToInt())
        }
    }

    fun displayMatrix(input: Array<Array<OctopusVertex>>) {
        for (hIndex in input.indices) {
            for (vIndex in input[hIndex].indices) {
                print("${input[hIndex][vIndex].num}")
            }
            println()
        }
        println()
    }

    fun addStep(
        input: Array<Array<OctopusVertex>>,
        hIndex: Int,
        vIndex: Int,
        glowingArray: MutableList<OctopusVertex>
    ) {
        val octopus = input[hIndex][vIndex]
        if (!octopus.isGlowing) {
            val num = ++octopus.num
            if (num > 9) {
                octopus.isGlowing = true
                glowingArray.add(octopus)

                val left = vIndex - 1
                val right = vIndex + 1
                val top = hIndex - 1
                val bottom = hIndex + 1

                if (left >= 0) {
                    addStep(input, hIndex, left, glowingArray)
                }

                if (right <= input[hIndex].size - 1) {
                    addStep(input, hIndex, right, glowingArray)
                }

                if (top >= 0) {
                    addStep(input, top, vIndex, glowingArray)
                }

                if (bottom <= input.size - 1) {
                    addStep(input, bottom, vIndex, glowingArray)
                }

                if (left >= 0 && top >= 0) {
                    addStep(input, top, left, glowingArray)
                }

                if (left >= 0 && bottom <= input.size - 1) {
                    addStep(input, bottom, left, glowingArray)
                }

                if (right <= input[hIndex].size - 1 && top >= 0) {
                    addStep(input, top, right, glowingArray)
                }

                if (right <= input[hIndex].size - 1 && bottom <= input.size - 1) {
                    addStep(input, bottom, right, glowingArray)
                }
            } else {
                input[hIndex][vIndex] = octopus
            }
        }
    }

    fun part1(input: Array<Array<OctopusVertex>>, steps: Int): Long {
        var sum = 0L
        //displayMatrix(input)

        for (step in 1..steps) {
            var glowingArray = mutableListOf<OctopusVertex>()

            for (hIndex in input.indices) {
                for (vIndex in input[hIndex].indices) {
                    addStep(input, hIndex, vIndex, glowingArray)
                }
            }

            sum += glowingArray.size
            for (octopus in glowingArray) {
                octopus.isGlowing = false
                octopus.num = 0
            }

            //displayMatrix(input)
        }

        //displayMatrix(input)

        return sum
    }


    fun part2(input: List<String>): Long {

        return 0L
    }

    println(part1(matrix, 100))
    //print(part2(input))
}

class OctopusVertex(var num: Int, var isGlowing: Boolean = false) {}



