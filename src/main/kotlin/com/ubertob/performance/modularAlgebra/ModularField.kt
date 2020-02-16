package com.ubertob.performance.modularAlgebra

data class ModularField(val modulo: Int) {

    fun applyFunction(f: (ModularNumber, ModularNumber) -> Boolean): (Int) -> PlaneGrid =
        { size ->
            PlaneGrid(size) { index ->
                val xMod = (index % size).toModularNumber()
                val yMod = (index / size).toModularNumber()
                f(xMod, yMod)
            }
        }

    fun Int.toModularNumber(): ModularNumber =
        ModularNumber(this % modulo, modulo)
}

