package com.example.sandbox

import com.example.sandbox.HackerRank.calculateDivisors
import com.example.sandbox.HackerRank.calculateMex
import com.example.sandbox.HackerRank.main
import org.junit.Assert.assertEquals
import org.junit.Test
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.PrintStream

class TowerBreakerTestCase {


    @Test
    fun testMain() {
        val input =
            "6\n" + "6\n" + "2 3 4 8 9 5\n" + "8\n" + "1 2 3 4 5 6 8 9\n" + "2\n" + "8 7\n" + "3\n" + "5 6 9\n" + "3\n" + "8484 848 949\n" + "5\n" + "541 98 151 948 325"
        val output = "1\n" + "2\n" + "1\n" + "1\n" + "1"
        val inputStream = ByteArrayInputStream(input.toByteArray())
        System.setIn(inputStream)
        val outputStream = ByteArrayOutputStream()
        System.setOut(PrintStream(outputStream))
        main()
        assertEquals(output, outputStream.toString().trim())
    }

}