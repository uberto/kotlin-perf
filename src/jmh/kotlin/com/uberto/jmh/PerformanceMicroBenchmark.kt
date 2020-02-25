package com.uberto.jmh

import com.ubertob.performance.knapsack.Knapsack
import com.ubertob.performance.knapsack.Watch
import com.ubertob.performance.mandelbrot.Complex
import com.ubertob.performance.mandelbrot.ZoomableView
import com.ubertob.performance.modularAlgebra.compareSquares
import org.openjdk.jmh.annotations.Benchmark

open class PerformanceMicroBenchmark {

    @Benchmark
    fun knapsack(){
        val shop = Knapsack.shop(
            Watch(20, 65),
            Watch(8, 35),
            Watch(60, 245),
            Watch(55, 195),
            Watch(40, 65),
            Watch(70, 150),
            Watch(85, 275),
            Watch(25, 155),
            Watch(30, 120),
            Watch(65, 320),
            Watch(75, 75),
            Watch(10, 40),
            Watch(95, 200),
            Watch(50, 100),
            Watch(40, 220),
            Watch(20, 99)
        )

        Knapsack.selectWatches(shop, 500)
    }


//    @Benchmark
    fun mandelbrot() {
        val center = Complex(-1.0, 0.0)
        val pa = ZoomableView(center, 0.5).toPointsArea(100, 100)
        pa.calculate(1000)
    }

//    @Benchmark
    fun modularAlgebra() {
        compareSquares(1000, 13)
    }

}


