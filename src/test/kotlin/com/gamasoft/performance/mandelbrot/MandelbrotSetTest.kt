package com.gamasoft.performance.mandelbrot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MandelbrotSetTest{


    @Test
    fun internalPointEscapeAtMaxIter(){

        val iter = mandelSet(origin, origin, 1000)

        assertEquals(1000, iter)
    }

    @Test
    fun externalPointEscapeImmediately(){

        val c = Complex(1.0, 0.0)
        val iter = mandelSet(c, c, 1000)

        assertEquals(1, iter)
    }



    @Test
    fun calculatePointsArea(){

        val center = Complex(-1.0, 0.0)
        val pa = ZoomableView(center, 0.25).toPointsArea(80,30)

        val matrix = pa.calculate(64)

        renderToStr(matrix).forEach{println(it)}

    }


    @Test
    fun performanceTest(){

        val center = Complex(-1.0, 0.0)
        val pa = ZoomableView(center, 0.5).toPointsArea(1000,1000)

        (1..100).forEach {

            val start = System.currentTimeMillis()
            val matrix = pa.calculate(1000)
            val elapsed = System.currentTimeMillis() - start

            println("1000x1000x1000 in $elapsed")

            renderToStr(matrix).first().also{println(it.slice(1..80))}
        }

        //1509   -Xms6g -Xmx6g -XX:+UseParallelGC
        //1279   -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler



    }

}
