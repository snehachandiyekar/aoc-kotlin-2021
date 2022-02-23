package com.snehachandiyekar.aockotlin2021

import java.util.*

fun main() {
    val input = readInputString("Day10.txt")
    val syntaxMap = mapOf('(' to ')', '[' to ']', '<' to '>', '{' to '}')
    val openSyntaxVal = mapOf(')' to 3, ']' to 57, '}' to 1197, '>' to 25137)
    val closeSyntaxVal = mapOf(')' to 1, ']' to 2, '}' to 3, '>' to 4)

    fun part1(input: List<String>): Long {
        var sum = 0L
        val keys = syntaxMap.keys

        for (row in input) {
            val syntaxStack = Stack<Char>()

            for (ch in row) {
                if (keys.contains(ch)) {
                    syntaxStack.push(ch)
                } else {
                    val peek = syntaxStack.peek()
                    if (syntaxMap[peek] == ch) {
                        syntaxStack.pop()
                    } else {
                        sum += openSyntaxVal[ch] ?: 0
                        //println("corrupt ch: $ch")
                        break
                    }
                }
            }
        }
        return sum
    }


    fun part2(input: List<String>): Long {
        val keys = syntaxMap.keys
        val scoreList = mutableListOf<Long>()

        for (row in input) {
            var sum = 0L
            var isCorrupted = false
            val syntaxStack = Stack<Char>()

            for (ch in row) {
                if (keys.contains(ch)) {
                    syntaxStack.push(ch)
                } else {
                    val peek = syntaxStack.peek()
                    if (syntaxMap[peek] == ch) {
                        syntaxStack.pop()
                    } else {
                        sum += openSyntaxVal[ch] ?: 0
                        isCorrupted = true
                        break
                    }
                }
            }

            if (!isCorrupted && !syntaxStack.isNullOrEmpty()) {
                while (syntaxStack.isNotEmpty()) {
                    val pop = syntaxMap[syntaxStack.pop()]
                    //print("$pop")
                    sum = sum * 5 + closeSyntaxVal[pop]!!
                }
                scoreList.add(sum)
                //println()
            }
        }

        return scoreList.sorted()[scoreList.size/2]
    }

    println(part1(input))
    print(part2(input))
}



