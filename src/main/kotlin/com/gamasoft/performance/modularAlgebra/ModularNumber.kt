package com.gamasoft.performance.modularAlgebra

data class ModularNumber(val num: Int, val modulo: Int): Comparable<ModularNumber>  {
    override fun compareTo(other: ModularNumber): Int = num.compareTo(other.num)

    operator fun plus(other: ModularNumber) = (num + other.num).toModularNumber()
    operator fun minus(other: ModularNumber) = (num - other.num).toModularNumber()
    operator fun times(other: ModularNumber) = (num * other.num).toModularNumber()
    operator fun div(other: ModularNumber) = ((1..modulo).find { (it * other.num) % modulo == (this.num % modulo) }!!).toModularNumber()

    fun squared() = (num*num).toModularNumber()

    fun Int.toModularNumber(): ModularNumber =
        ModularNumber(this % modulo, modulo)

}

