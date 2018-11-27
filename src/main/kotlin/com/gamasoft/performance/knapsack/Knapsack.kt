package com.gamasoft.performance.knapsack

import java.util.*;

class Knapsack {

    companion object {

        fun shop(vararg watches: Watch): Set<Watch> = watches.toHashSet()

        fun select(shop: Set<Watch>, maxWeight: Int): Int =
            select(KnapMemo(), shop, maxWeight, emptySet())

        fun select(memo: KnapMemo, shop: Set<Watch>, maxWeight: Int, choice: Set<Watch>): Int {
            val key = generateKey(choice)

            val best = memo.best(key, maxWeight);
            if (best > -1)
                return best

            var maxVal = choice.sumBy { it.price }.or(0);

            for (w: Watch in shop) {
                if (!(w in choice) && w.weight <= maxWeight) {
                    val nc = choice + w
                    val value = select(memo, shop, maxWeight - w.weight, nc)
                    if (value > maxVal)
                        maxVal = value
                }
            }

            memo.storeBest(key, maxWeight, maxVal)
            return maxVal
        }

        private fun generateKey(choice: Set<Watch>) =
            choice.sortedBy { "${it.price}-${it.weight}" }.toString()

    }
}


data class Watch(val weight: Int, val price: Int)


class KnapMemo : MutableMap<String, SortedMap<Int, Int>> by HashMap() {

    fun best(key: String, maxWeight: Int): Int =

        when (val solutions = get(key)) {
            null -> -1
            else -> when (val solution = solutions.get(maxWeight)){
                null -> solutions.headMap(maxWeight).let{
                                it.getOrDefault(it.lastKey(), -1) //biggest of all smaller than
                            }
                else -> solution
            }
        }


    fun storeBest(key: String, maxWeight: Int, value: Int) {
        val solutions = getOrDefault(key, TreeMap())

        solutions.entries.removeIf { it.key >= maxWeight && it.value <= value }

        solutions.put(maxWeight, value)
        put(key, solutions)

//        println("best solutions $size")
    }
}

