package com.ubertob.performance.modularAlgebra

data class ModularNumber(val num: Int, val modulo: Int): Comparable<ModularNumber>  {

    override fun compareTo(other: ModularNumber): Int = num.compareTo(other.num)

    operator fun plus(other: ModularNumber) = (num + other.num).toModularNumber()
    operator fun minus(other: ModularNumber) = (num - other.num).toModularNumber()
    operator fun times(other: ModularNumber) = (num * other.num).toModularNumber()
    operator fun div(other: ModularNumber) = ((1..modulo).first { (it * other.num) % modulo == (this.num % modulo) }).toModularNumber()
    operator fun inc() = (num + 1).toModularNumber()

    fun squared() = (num*num).toModularNumber()

    fun Int.toModularNumber(): ModularNumber =
        ModularNumber(this % modulo, modulo)

}

