package com.gamasoft.performance.modularAlgebra

data class ModularField(val modulo: Int) {

    fun Int.toModularNumber() = ModularNumber(this, modulo)


    fun PlaneGrid.applyFunction(f: (ModularNumber, ModularNumber) -> Boolean) =
        this.also{
            for(x in 1 .. size) {
                for(y in 1 .. size) {
                    this[x, y] = f(x.toModularNumber(), y.toModularNumber())
                }
            }
        }

}