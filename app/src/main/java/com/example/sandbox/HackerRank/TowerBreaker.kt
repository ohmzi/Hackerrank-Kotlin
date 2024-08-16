package com.example.sandbox.HackerRank

//https://www.hackerrank.com/challenges/tower-breakers-again-1/problem
import java.util.Scanner

const val MAX_NUMBER = 100001

val memoizedValues = IntArray(MAX_NUMBER) { -1 }
val numberDivisors = Array(MAX_NUMBER) { mutableListOf<Int>() }

fun calculateDivisors() {
    for (i in 2 until MAX_NUMBER) {
        for (j in i until MAX_NUMBER step i) {
            numberDivisors[j].add(i)
        }
    }
}

fun calculateMex(x: Int): Int {
    if (x == 1) return 0
    if (memoizedValues[x] != -1) return memoizedValues[x]
    val possibleValues = mutableSetOf<Int>()
    for (divisor in numberDivisors[x]) {
        if (divisor % 2 == 0) {
            possibleValues.add(0)
        } else {
            val towerHeight = x / divisor
            possibleValues.add(calculateMex(towerHeight))
        }
    }
    var mex = 0
    while (possibleValues.contains(mex)) mex++
    memoizedValues[x] = mex
    return mex
}

fun main() {
    calculateDivisors()
    val scanner = Scanner(System.`in`)
    val testCases = scanner.nextInt()
    for (i in 1..testCases) {
        val towerCount = scanner.nextInt()
        var result = 0
        for (j in 1..towerCount) {
            result = result xor calculateMex(scanner.nextInt())
        }
        println(if (result != 0) "1" else "2")
    }
}