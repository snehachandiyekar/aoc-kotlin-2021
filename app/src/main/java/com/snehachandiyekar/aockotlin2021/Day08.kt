package com.snehachandiyekar.aockotlin2021

/*
0:      1:      2:      3:      4:
 aaaa    ....    aaaa    aaaa    ....
b    c  .    c  .    c  .    c  b    c
b    c  .    c  .    c  .    c  b    c
 ....    ....    dddd    dddd    dddd
e    f  .    f  e    .  .    f  .    f
e    f  .    f  e    .  .    f  .    f
 gggg    ....    gggg    gggg    ....

  5:      6:      7:      8:      9:
 aaaa    aaaa    aaaa    aaaa    aaaa
b    .  b    .  .    c  b    c  b    c
b    .  b    .  .    c  b    c  b    c
 dddd    dddd    ....    dddd    dddd
.    f  e    f  .    f  e    f  .    f
.    f  e    f  .    f  e    f  .    f
 gggg    gggg    ....    gggg    gggg
 */

fun main() {
    val input1 = readInputString("Day08.txt")

    fun part1(input: List<String>): Int {
        var count = 0
        for (row in input) {
            val op: List<String> = row.split("|")[1].split(" ")
            for (item in op) {
                if (item.length == 2
                    || item.length == 3
                    || item.length == 4
                    || item.length == 7
                ) {
                    count++
                }
            }
        }
        return count
    }

    val defaultMap = listOf<String>(
        "1110111",
        "0000011",
        "0111110",
        "0011111",
        "1001011",
        "1011101",
        "1111101",
        "0010011",
        "1111111",
        "1011111"
    )

    fun assign_5_6(input: List<String>, numMap: CharArray) {     //from 1
        numMap[5] = input[1][0]
        numMap[6] = input[1][1]
    }

    fun assign_2(input: List<String>, numMap: CharArray) {      //from 7
        val val2 = input[2].replace(numMap[5].toString(), "")
            .replace(numMap[6].toString(), "")
        numMap[2] = val2[0]
    }

    fun assign_0_3_4(
        input: List<String>,
        val5: MutableList<String>,
        numMap: CharArray
    ) {                                 //from 4 and 2, 3, 5

        val val4 = input[3].replace(numMap[5].toString(), "")
            .replace(numMap[6].toString(), "")

        for (i in val5) {
            val item = i.replace(numMap[5].toString(), "")
                .replace(numMap[6].toString(), "")

            if (item.length == 3) {
                val index1 = item.indexOf(val4[0])
                val index2 = item.indexOf(val4[1])
                if (index1 != -1) {
                    numMap[3] = item[index1]
                } else if (index2 != -1) {
                    numMap[3] = item[index2]
                }
                numMap[0] = val4.replace(numMap[3].toString(), "")[0]
                numMap[4] =
                    item.replace(numMap[2].toString(), "")
                        .replace(numMap[3].toString(), "")[0]
            }
        }
    }

    fun reorder_5_6(val5: MutableList<String>, numMap: CharArray) {     //from 2, 3, 5 and 1
        for (i in val5) {
            val item = i.replace(numMap[5].toString(), "")
                .replace(numMap[6].toString(), "")
                .replace(numMap[0].toString(), "")
                .replace(numMap[4].toString(), "")

            if (item.length == 3) {
                val index1 = i.indexOf(numMap[5])
                val index2 = i.indexOf(numMap[6])
                var pos = numMap[5]
                if (index1 != -1) {
                    pos = i[index1]
                } else if (index2 != -1) {
                    pos = i[index2]
                }

                if (pos == numMap[6]) {
                    val temp = numMap[5]
                    numMap[5] = numMap[6]
                    numMap[6] = temp
                }
            }
        }
    }

    fun assign_1(input: List<String>, numMap: CharArray) {       //from 8
        numMap[1] = input[10].replace(numMap[0].toString(), "")
            .replace(numMap[2].toString(), "")
            .replace(numMap[3].toString(), "")
            .replace(numMap[4].toString(), "")
            .replace(numMap[5].toString(), "")
            .replace(numMap[6].toString(), "")[0]
    }

    fun getFourDigitNumber(
        output: List<String>,
        numMap: CharArray
    ): StringBuilder {
        var num1 = StringBuilder()
        for (item in output) {
            if (item.isEmpty())
                continue

            val opMap = CharArray(7) { '0' }
            val charArray = item.toCharArray()
            for (ch in charArray) {
                opMap[numMap.indexOf(ch)] = '1'
            }

            val key = opMap.contentToString().replace("[", "")
                .replace("]", "")
                .replace(",", "")
                .replace(" ", "")

            val temp = defaultMap.indexOf(key)
            num1 = num1.append(temp)
        }
        return num1
    }

    fun part2(input1: List<String>): Long {
        var count = 0L
        for (row in input1) {
            val numMap = CharArray(7) { ' ' }
            val op: List<String> = row.split("|")
            val input = op[0].split(" ").sortedBy { it.length }
            val output = op[1].split(" ").drop(0)

            //0 2 3 4 5 5 5 6 6 6 7 (number of digits)

            assign_5_6(input, numMap)

            assign_2(input, numMap)

            val val5 = mutableListOf<String>()
            val5.add(input[4])
            val5.add(input[5])
            val5.add(input[6])

            assign_0_3_4(input, val5, numMap)

            reorder_5_6(val5, numMap)
            assign_1(input, numMap)

            val num = getFourDigitNumber(output, numMap)
            count += num.toString().toInt()
        }
        return count
    }

    //println(part1(input))
    println(part2(input1))

}