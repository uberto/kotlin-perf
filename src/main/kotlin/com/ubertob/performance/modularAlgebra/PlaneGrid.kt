package com.ubertob.performance.modularAlgebra

data class PlaneGrid(val size: Int, val initPredicate: (Int) -> Boolean) {

    private val grid = BooleanArray(size*size, initPredicate)

    operator fun get(x: Int, y: Int): Boolean =
        grid[coord(x,y)]

    private fun coord(x: Int, y: Int) = (y-1) * size + x -1

    fun count(): Int = grid.count {it}

}