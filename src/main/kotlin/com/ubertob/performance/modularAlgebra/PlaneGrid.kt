package com.ubertob.performance.modularAlgebra

data class PlaneGrid(val size: Int, val initPredicate: (Int) -> Boolean) {

    private val grid = BooleanArray(size*size, initPredicate)

    operator fun get(x: Int, y: Int): Boolean = grid[coord(x,y)]

    private fun coord(x: Int, y: Int) = (y-1) * size + x -1

    fun count(): Int = grid.count {it}

}

val compareSquares: (ModularNumber, ModularNumber) -> Boolean =
    { x, y -> x.squared() >= y.squared() }

fun compareSquares(size: Int, modulo: Int): Int =
    ModularField(modulo).applyFunction(compareSquares)(size).count()


fun sumOfFunction(from: Int, to: Int, f: (Int) -> Int) =
    (from..to)
        .map { f(it) }
        .sum()

fun compareSquaresUgly(size: Int, modulo: Int): Int {
    val boolGrid = BooleanArray(size * size)
    //            unknownFun(boolGrid)
    for (x in 1..size) {
        for (y in 1..size) {
            boolGrid[(y - 1) * size + x - 1] = (x * x) % modulo >= (y * y) % modulo
        }
    }
    var tot = 0
    for (i in 0 until boolGrid.size) {
        if (boolGrid[i]) tot++
    }
    return tot
}
