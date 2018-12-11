package com.gamasoft.performance.modularAlgebra

data class ModularField(val modulo: Int) {

    fun Int.toModularNumber() = ModularNumber(this, modulo)

    fun applyFunction(plane: PlaneGrid, f: (ModularNumber, ModularNumber) -> Boolean) {
        (1.. plane.size).forEach{x->
            (1..plane.size).forEach{y->
                plane[x,y] = f(x.toModularNumber(), y.toModularNumber())
            }
        }
    }

}