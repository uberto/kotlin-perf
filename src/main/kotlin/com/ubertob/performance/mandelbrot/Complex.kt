package com.ubertob.performance.mandelbrot

data class Complex(val r: Double, val i: Double): Comparable<Complex> {

    override fun compareTo(other: Complex): Int = squaredModule().compareTo(other.squaredModule())

    operator fun times(other: Complex) =
        Complex(
            r = this.r * other.r - this.i * other.i,
            i = this.i * other.r + this.r * other.i
        )
    operator fun plus(other: Complex) =
        Complex(r = this.r + other.r, i = this.i + other.i)
    operator fun minus(other: Complex) =
        Complex(r = this.r - other.r, i = this.i - other.i)
    fun squared() = this * this
    fun squaredModule() = r * r + i * i
    fun Double.toComplex() = Complex(r=this, i=0.0)
}

val origin = Complex(0.0, 0.0)