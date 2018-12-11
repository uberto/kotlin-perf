package com.gamasoft.performance.modularAlgebra

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ModularAlgebraTest {
    val compareSquares: (ModularNumber, ModularNumber) -> Boolean =
        { x, y -> x.squared() >= y.squared() }

    @Test
    fun drawThePlane(){

        val size = 20

        (3..50).forEach {
            val grid = PlaneGrid(size)

            ModularField(it).apply {
                grid.applyFunction(compareSquares)
            }

            println("Modulo $it")
            (1 .. size).forEach { y->
                println( (1 .. size)
                    .map {x -> if (grid[x,y]) "@" else " "  }
                    .joinToString(separator = "") )
            }
        }
    }

    @Test
    fun performanceTest() {
        val size = 1000

        //100*100*10000 = 100M operations for run (about 1s)

        (1..20).forEach {
            val start = System.currentTimeMillis()
            assertEquals(7136703, sumOfCompareSquares(size, 10))
            assertEquals(12991154, sumOfCompareSquares(size, 20))
            assertEquals(18549917, sumOfCompareSquares(size, 30))
            assertEquals(24010171, sumOfCompareSquares(size, 40))
            val elapsed = System.currentTimeMillis() - start
            println("Modular Algebra of size $size  -> $elapsed   (${Runtime.getRuntime().freeMemory()/1000000})")
        }
        //100*100*10000
        //855   -Xms6g -Xmx6g -XX:+UseParallelGC
        //1170   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
        //1000*1000*100
        //810   -Xms6g -Xmx6g -XX:+UseParallelGC
        //1191   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler

    }

    private fun sumOfCompareSquares(size: Int, upTo: Int) =
        (1..upTo).map {
            ModularField(it).run {
                PlaneGrid(size)
                .applyFunction(compareSquares)
                .count()
            }
        }.sum()

}