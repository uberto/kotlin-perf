package com.gamasoft.performance.modularAlgebra

data class ModularField(val modulo: Int) {

    fun Int.toModularNumber() = ModularNumber(this, modulo)


    fun PlaneGrid.applyFunction(f: (ModularNumber, ModularNumber) -> Boolean) =
        (1..size).forEach { x ->
            (1..size).forEach { y ->
                this[x, y] = f(x.toModularNumber(), y.toModularNumber())
            }
        }.let {this}

}