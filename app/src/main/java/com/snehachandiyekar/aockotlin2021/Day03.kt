package com.snehachandiyekar.aockotlin2021

//gamma rate
//epsilon rate
//power consumption
//life support

fun main() {
    val input = readInputString("Day03.txt")
    val OXYGEN_RATING = 1
    val CO2_RATING = 0

    fun part1(input: List<String>): Int {
        val gBit = StringBuilder()
        val eBit = StringBuilder()
        var oneCount: Int
        var zeroCount: Int

        val bitLength = input[0].length
        for (pos in 0 until bitLength) {
            oneCount = 0
            zeroCount = 0
            for (i in input.indices) {
                if (input[i][pos] == '1') {
                    oneCount++
                } else {
                    zeroCount++
                }
            }
            if (oneCount > zeroCount) {
                gBit.append("1")
                eBit.append("0")
            } else {
                gBit.append("0")
                eBit.append("1")
            }
        }

        val gammaRate = gBit.toString().toInt(2)
        val epsilonRate = eBit.toString().toInt(2)

        return gammaRate * epsilonRate
    }

    fun findRating(input: List<String>, pos: Int, rating: Int): Int {
        if (input.size == 1) {
            return input[0].toInt(2)
        }

        val oneList = mutableListOf<String>()
        val zeroList = mutableListOf<String>()

        for (reading in input) {
            if (reading[pos] == '1') {
                oneList.add(reading)
            } else {
                zeroList.add(reading)
            }
        }

        if (rating == OXYGEN_RATING && oneList.size >= zeroList.size) {
            return findRating(oneList, pos + 1, rating)

        } else if (rating == CO2_RATING && oneList.size < zeroList.size) {
            return findRating(oneList, pos + 1, rating)

        } else {
            return findRating(zeroList, pos + 1, rating)
        }
    }

    fun part2(input: List<String>): Int {
        val oxyRate = findRating(input, 0, OXYGEN_RATING)
        val co2Rate = findRating(input, 0, CO2_RATING)
        println("$oxyRate $co2Rate")
        return oxyRate * co2Rate
    }

    println(part1(input))
    println(part2(input))
}