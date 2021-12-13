package com.snehachandiyekar.aockotlin2021

import java.io.File

fun readInput(name: String) = File("inputs", name).readLines().map { it.toInt() }

fun readInputString(name: String) = File("inputs", name).readLines()
