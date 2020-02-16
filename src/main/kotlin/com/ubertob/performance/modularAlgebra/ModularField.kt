package com.ubertob.performance.modularAlgebra

data class ModularField(val modulo: Int) {


    fun applyFunction(f: (ModularNumber, ModularNumber) -> Boolean): (Int) -> PlaneGrid =
        { size ->
            var xMod = ModularNumber(0, module)
            var yMod = ModularNumber(1, module)
            PlaneGrid(size) { index ->
                xMod = xMod.inc()
                if (xMod.num % size == 0) yMod = yMod.inc()
                f(xMod, yMod)
            }
        }
}

