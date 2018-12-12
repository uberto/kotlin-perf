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
  //          assertEquals(713647050, sumOfCompareSquares(size, 1,10)) for 10000
            assertEquals(55563910, sumOfFunction(1,100){x -> compareSquaresUgly(size, x)})
            assertEquals(102209675, sumOfFunction(101, 300){x -> compareSquaresUgly(size, x)})
            assertEquals(151591862, sumOfFunction( 301, 600){x -> compareSquaresUgly(size, x)})
            assertEquals(201271117, sumOfFunction(601, 1000){x -> compareSquaresUgly(size, x)})
            val elapsed = System.currentTimeMillis() - start
            println("Modular Algebra of size $size  -> $elapsed   (${Runtime.getRuntime().freeMemory()/1000000})")
        }
        //1000*1000*1000
        //7098   -Xms6g -Xmx6g -XX:+UseParallelGC
        //11083   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
        //1000*1000*1000 Ugly
        //5188   -Xms6g -Xmx6g -XX:+UseParallelGC
        //7998   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
        //1000*1000*100
        //624   -Xms6g -Xmx6g -XX:+UseParallelGC
        //983   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
        //1000*1000*100 sumOfCompareSquaresUgly
        //666   -Xms6g -Xmx6g -XX:+UseParallelGC
        //975   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
        //10000*10000*10
        //19402   -Xms6g -Xmx6g -XX:+UseParallelGC
        //16034   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler

    }

    private fun compareSquares(size: Int, modulo: Int) =
            ModularField(modulo).run {
                PlaneGrid(size)
                    .applyFunction(compareSquares)
                    .count()
            }


    private fun sumOfFunction(from: Int, to: Int, f: (Int) -> Int) =
        (from..to)
            .map { f(it) }
            .sum()

    private fun compareSquaresUgly(size: Int, modulo: Int): Int {
        val boolGrid = BooleanArray(size * size)
        //            unknownFun(boolGrid)
        for (x in 1..size) {
            for (y in 1..size) {
                boolGrid[(y - 1) * size + x - 1] = (x * x) % modulo >= (y * y) % modulo
            }
        }
        var tot = 0
        for (i in 0 until boolGrid.size) {
            if (boolGrid[i]) tot++
        }
        return tot
    }

    private fun unknownFun(boolGrid: BooleanArray) {
        lastGrid = boolGrid
    }

    lateinit var lastGrid: BooleanArray

}