package com.snehachandiyekar.aockotlin2021

fun main() {
    val input = readInputString("Day09.txt")

    fun part1(input: List<String>): Long {
        var sum = 0L
        for (hIndex in input.indices) {
            for (vIndex in input[hIndex].indices) {

                val num = input[hIndex][vIndex].digitToInt()
                val left = vIndex - 1
                val right = vIndex + 1
                val top = hIndex - 1
                val bottom = hIndex + 1

                if (left >= 0 && input[hIndex][left].digitToInt() <= num) {
                    continue
                }

                if (right <= input[hIndex].length - 1 && input[hIndex][right].digitToInt() <= num) {
                    continue
                }

                if (top >= 0 && input[top][vIndex].digitToInt() <= num) {
                    continue
                }

                if (bottom <= input.size - 1 && input[bottom][vIndex].digitToInt() <= num) {
                    continue
                }

                sum += num + 1
                //println("num: $num ($left,$top,$right,$bottom) sum: $sum ")


                /*if (hIndex == 0 && vIndex == 0) {
                    val right = input[hIndex][vIndex + 1].digitToInt()
                    val bottom = input[hIndex + 1][vIndex].digitToInt()

                    if (right > num
                        && bottom > num
                    ) {
                        sum += num + 1

                    }
                } else if (hIndex == input.size - 1 && vIndex == input[hIndex].length - 1) {
                    val left = input[hIndex][vIndex - 1].digitToInt()
                    val top = input[hIndex - 1][vIndex].digitToInt()

                    if (left > num
                        && top > num
                    ) {
                        sum += num + 1

                    }
                } else if (hIndex == input.size - 1 && vIndex == 0) {
                    val right = input[hIndex][vIndex + 1].digitToInt()
                    val top = input[hIndex - 1][vIndex].digitToInt()

                    if (right > num
                        && top > num
                    ) {
                        sum += num + 1

                    }
                } else if (hIndex == 0 && vIndex == input[hIndex].length - 1) {
                    val left = input[hIndex][vIndex - 1].digitToInt()
                    val bottom = input[hIndex + 1][vIndex].digitToInt()

                    if (left > num
                        && bottom > num
                    ) {
                        sum += num + 1

                    }
                } else if (hIndex == 0 && vIndex > 0 && vIndex < input[hIndex].length - 1) {
                    val left = input[hIndex][vIndex - 1].digitToInt()
                    val right = input[hIndex][vIndex + 1].digitToInt()
                    val bottom = input[hIndex + 1][vIndex].digitToInt()

                    if (left > num
                        && right > num
                        && bottom > num
                    ) {
                        sum += num + 1

                    }
                } else if (hIndex > 0 && hIndex < input.size - 1 && vIndex == 0) {
                    val right = input[hIndex][vIndex + 1].digitToInt()
                    val top = input[hIndex - 1][vIndex].digitToInt()
                    val bottom = input[hIndex + 1][vIndex].digitToInt()

                    if (right > num
                        && top > num
                        && bottom > num
                    ) {
                        sum += num + 1

                    }
                } else if (hIndex == input.size - 1 && vIndex > 0 && vIndex < input[hIndex].length - 1) {
                    val left = input[hIndex][vIndex - 1].digitToInt()
                    val right = input[hIndex][vIndex + 1].digitToInt()
                    val top = input[hIndex - 1][vIndex].digitToInt()

                    if (left > num
                        && right > num
                        && top > num
                    ) {
                        sum += num + 1

                    }
                } else if (hIndex < input.size - 1 && hIndex > 0 && vIndex == input[hIndex].length - 1) {
                    val left = input[hIndex][vIndex - 1].digitToInt()
                    val top = input[hIndex - 1][vIndex].digitToInt()
                    val bottom = input[hIndex + 1][vIndex].digitToInt()

                    if (left > num
                        && top > num
                        && bottom > num
                    ) {
                        sum += num + 1

                    }
                } else {
                    val left = input[hIndex][vIndex - 1].digitToInt()
                    val right = input[hIndex][vIndex + 1].digitToInt()
                    val top = input[hIndex - 1][vIndex].digitToInt()
                    val bottom = input[hIndex + 1][vIndex].digitToInt()

                    if (left > num
                        && right > num
                        && top > num
                        && bottom > num
                    ) {
                        sum += num + 1

                    }
                }*/
            }
        }

        return sum
    }

    fun part2(input: List<Int>): Int {
        var count = 0

        return count
    }

    println(part1(input))
    //print(part2(input))
}


