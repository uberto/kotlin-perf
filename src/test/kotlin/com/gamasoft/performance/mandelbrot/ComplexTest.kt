package com.gamasoft.performance.mandelbrot

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ComplexTest{

    @Test
    fun add2ComplexNumbers(){

        val r = Complex(12.0, -4.0) - Complex(9.0, 3.5)

        assertEquals(Complex(3.0, -7.5), r)
    }


    @Test
    fun multiply2ComplexNumbers(){

        (Complex(1.0, 0.0) * Complex(0.0, 1.0))
            .let{ assertEquals(Complex(0.0, 1.0), it)}

        (Complex(2.0, 1.0) * Complex(3.0, 1.0))
            .let{ assertEquals(Complex(5.0, 5.0), it)}
    }
}