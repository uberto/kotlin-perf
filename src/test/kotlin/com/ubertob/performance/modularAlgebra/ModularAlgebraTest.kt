package com.ubertob.performance.modularAlgebra

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ModularAlgebraTest {
    val compareSquares: (ModularNumber, ModularNumber) -> Boolean =
        { x, y -> x.squared() >= y.squared() }

    @Test
    fun calculateSquaresMod1(){
        val total = sumOfFunction(1, 1) { x -> compareSquares(1000, x) }
        assertEquals(1000000, total)
    }

    @Test
    fun calculateSquaresMod2(){
        val total = sumOfFunction(2, 2) { x -> compareSquares(1000, x) }
        assertEquals(750000, total)
    }

    @Test
    fun calculateSquaresMod5(){
        val total = sumOfFunction(5, 5) { x -> compareSquares(1000, x) }
        assertEquals(680000, total)
    }

    @Test
    fun drawThePlane() {

        val size = 20

        (3..50).forEach {
            val grid = ModularField(it).applyFunction(compareSquares)(size)

            println("Modulo $it")
            (1..size).forEach { y ->
                println((1..size)
                    .map { x -> if (grid[x, y]) "@" else " " }
                    .joinToString(separator = ""))
            }
        }
    }

    @Test
    fun performanceTest() {
        val size = 1000

        //100*100*10000 = 100M operations for run (about 1s)

        (1..20).forEach {
            val start = System.currentTimeMillis()
            assertEquals(55561084, sumOfFunction(1, 100) { x -> compareSquares(size, x) })
            assertEquals(102208112, sumOfFunction(101, 300) { x -> compareSquares(size, x) })
            assertEquals(151590652, sumOfFunction(301, 600) { x -> compareSquares(size, x) })
            assertEquals(201270190, sumOfFunction(601, 1000) { x -> compareSquares(size, x) })


            val elapsed = System.currentTimeMillis() - start
            println("Modular Algebra of size $size  -> $elapsed ms.  (freemem ${Runtime.getRuntime().freeMemory() / 1000000})")
        }
        //Graal 19.3 size 1000
        //11026 ms.
        // -Xms6g -Xmx6g -Dgraal.ShowConfiguration=info -XX:+UseParallelOldGC -XX:+AlwaysPreTouch -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
        //16272 ms.
        // -Xms6g -Xmx6g -Dgraal.ShowConfiguration=info -XX:+UseParallelOldGC -XX:+AlwaysPreTouch -XX:+UnlockExperimentalVMOptions -XX:-UseJVMCICompiler

    }

    private fun compareSquares(size: Int, modulo: Int): Int =
        ModularField(modulo).applyFunction(compareSquares)(size).count()


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

}