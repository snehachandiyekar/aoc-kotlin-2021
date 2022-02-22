package com.snehachandiyekar.aockotlin2021

import java.util.*

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

    fun validateInput(
        input: Location,
        locStack: Stack<Location>
    ) {
        if (input.num != 9 && !input.isVisited) {
            input.isVisited = true
            locStack.push(input)
        }
    }

    fun part2(input: List<String>): Long {
        val basinInput = mutableListOf<List<Location>>()
        val lowPointList = mutableListOf<Location>()

        for (hIndex in input.indices) {
            val basinInput1 = mutableListOf<Location>()

            for (vIndex in input[hIndex].indices) {

                val num = input[hIndex][vIndex].digitToInt()
                val left = vIndex - 1
                val right = vIndex + 1
                val top = hIndex - 1
                val bottom = hIndex + 1

                basinInput1.add(Location(num, hIndex, vIndex))

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

                lowPointList.add(Location(num, hIndex, vIndex))
            }
            basinInput.add(basinInput1)
        }

        val basinCount = mutableListOf<Int>()

        for (point in lowPointList) {
            val locStack = Stack<Location>()
            basinInput[point.x][point.y].isVisited = true
            locStack.push(basinInput[point.x][point.y])

            var count = 0
            while (locStack.isNotEmpty()) {
                val loc = locStack.pop()
                count++
                val hIndex = loc.x
                val vIndex = loc.y

                val left = vIndex - 1
                val right = vIndex + 1
                val top = hIndex - 1
                val bottom = hIndex + 1

                if (left >= 0) {
                    validateInput(basinInput[hIndex][left], locStack)
                }

                if (right <= input[hIndex].length - 1) {
                    validateInput(basinInput[hIndex][right], locStack)
                }

                if (top >= 0) {
                    validateInput(basinInput[top][vIndex], locStack)
                }

                if (bottom <= input.size - 1) {
                    validateInput(basinInput[bottom][vIndex], locStack)
                }
            }

            basinCount.add(count)
        }

        basinCount.sort()
        val length = basinCount.size
        return (basinCount[length - 1] * basinCount[length - 2] * basinCount[length - 3]).toLong()
    }

    println(part1(input))
    print(part2(input))
}

class Location(var num: Int, var x: Int, var y: Int, var isVisited: Boolean = false)


