package com.gamasoft.performance.knapsack

class Knapsack {

    companion object {

        fun shop(vararg watches: Watch): Set<Watch> = watches.toHashSet()

        fun selectWatch(shop: Set<Watch>, maxWeight: Int): Int =
            selectWatch(mutableMapOf(), shop, maxWeight, emptySet())

        fun priceAddingElement(memo: MutableMap<String, Int>, shop: Set<Watch>, choice: Set<Watch>, maxWeight: Int, priceSum: Int): Int =
            shop
                .filter { !(it in choice) && it.weight <= maxWeight }
                .map {
                    selectWatch(
                        memo,
                        shop,
                        maxWeight - it.weight,
                        choice + it) }
                .filter { it > priceSum }
                .max() ?: priceSum


        fun selectWatch(memo: MutableMap<String, Int>, shop: Set<Watch>, maxWeight: Int, choice: Set<Watch>): Int =
            memoization(memo, generateKey(choice)) {
                priceAddingElement(memo, shop, choice, maxWeight, choice.sumBy { it.price })}


        private fun memoization(memo: MutableMap<String, Int>, key: String, f: () -> Int): Int {

            memo[key]?.let { return it }

            return f().also { memo[key] = it }
        }


        private fun generateKey(choice: Set<Watch>) =
            choice.sortedBy { "${it.price}-${it.weight}" }.toString()

    }
}


data class Watch(val weight: Int, val price: Int)
