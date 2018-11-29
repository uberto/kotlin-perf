package com.gamasoft.performance.mandelbrot

data class Complex(val r: Double, val i: Double){
    operator fun times(other: Complex) =
        Complex(
            r = this.r * other.r - this.i * other.i,
            i = this.i * other.r + this.r * other.i
        )
    operator fun plus(other: Complex) =
        Complex(r = this.r + other.r, i = this.i + other.i)
    operator fun minus(other: Complex) =
        Complex(r = this.r - other.r, i = this.i - other.i)
    fun Complex.square() = this * this
    fun Complex.squaredModule() = r * r + i * i
}