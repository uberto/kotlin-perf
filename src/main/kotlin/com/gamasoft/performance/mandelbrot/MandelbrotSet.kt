package com.gamasoft.performance.mandelbrot


data class ZoomableView(val center: Complex, val magnification: Double){

    fun toPixelArea(width: Int, height: Int): PixelArea = PixelArea(
        width = width,
        height = height,
        topLeftCoord = center,
        pixelIncrement = magnification / width //assuming full width == 1.0
    )
}

data class PixelArea(val width: Int, val height: Int, val topLeftCoord: Complex, val pixelIncrement: Double)
