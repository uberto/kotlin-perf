package com.gamasoft.performance.modularAlgebra

data class PlaneGrid(val size: Int) {

    private val grid = BooleanArray(size*size){false}

    operator fun set(x: Int, y: Int, value: Boolean) {
        grid[coord(x,y)] = value
    }

    operator fun get(x: Int, y: Int): Boolean =
        grid[coord(x,y)]

    private fun coord(x: Int, y: Int) = (y-1) * size + x -1




    fun count(): Int = grid.filter {it}.count()



}