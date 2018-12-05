package com.gamasoft.performance.mandelbrot


data class ZoomableView(val center: Complex, val magnification: Double){

    fun toPointsArea(width: Int, height: Int): PointsArea = PointsArea(
        width = width,
        height = height,
        topLeftCoord = center - Complex(1/(2 * magnification), (height.toDouble() / width) / (2 * magnification)),
        pixelIncrement = 1 / (magnification * width) //assuming full width == 1.0
    )
}

data class PointsArea(val width: Int, val height: Int, val topLeftCoord: Complex, val pixelIncrement: Double) {

    fun calculate(maxIter: Int):Array<Array<Int>> =

        Array(height){Array(width){0}}.apply{

        (1..height).forEach { y ->
            (1..width).forEach { x ->
                val c = Complex(
                    x * pixelIncrement + topLeftCoord.r,
                    y * pixelIncrement + topLeftCoord.i
                )
                val iter = mandelSet(origin, c, maxIter)
                this[y - 1][x - 1] = iter
            }
        }
    }
}


fun mandelSet(initZ: Complex, c:Complex, maxIter: Int): Int {
    var z = initZ
    (1..maxIter).forEach{
        z = z.squared() + c
        if (z.squaredModule() >= 4)
            return it
    }
    return maxIter
}

fun renderToStr(matrix: Array<Array<Int>>): List<String> =
    matrix.map { it.map{ (32+ 64 - it).toChar() }.joinToString("") }
