package com.gamasoft.performance.knapsack

class Knapsack {

    companion object {

        fun shop(vararg watches: Watch): Set<Watch> = watches.toHashSet()

        fun selectWatch(shop: Set<Watch>, maxWeight: Int): Int =
            selectWatch(mutableMapOf(), shop, maxWeight, emptySet())

        fun selectWatch(memo: MutableMap<String, Int>, shop: Set<Watch>, maxWeight: Int, choice: Set<Watch>): Int {
            val key = generateKey(choice)

            memo[key]?.apply { return this }

            var maxVal = choice.sumBy { it.price }

            for (w: Watch in shop) {
                if (!(w in choice) && w.weight <= maxWeight) {
                    val nc = choice + w
                    val value = selectWatch(memo, shop, maxWeight - w.weight, nc)
                    if (value > maxVal)
                        maxVal = value
                }
            }

            memo.put(key, maxVal)
            return maxVal
        }

        private fun generateKey(choice: Set<Watch>) =
            choice.sortedBy { "${it.price}-${it.weight}" }.toString()

    }
}


data class Watch(val weight: Int, val price: Int)
