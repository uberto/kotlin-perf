package com.gamasoft.performance.knapsack

typealias Memoizer = MutableMap<Int, Int>

class Knapsack {

    companion object {

        fun shop(vararg watches: Watch): Set<Watch> = watches.toHashSet()

        fun selectWatch(shop: Set<Watch>, maxWeight: Int): Int =
            selectWatch(mutableMapOf(), shop, maxWeight, emptySet(), 0)

        fun priceAddingElement(memo: Memoizer, shop: Set<Watch>, choice: Set<Watch>, maxWeight: Int, priceSum: Int): Int =
            shop
                .filter { !(it in choice) && it.weight <= maxWeight }
                .map {
                    selectWatch(
                        memo,
                        shop,
                        maxWeight - it.weight,
                        choice + it,
                        priceSum + it.price) }
                .filter { it > priceSum }
                .max() ?: priceSum


        fun selectWatch(memo: Memoizer, shop: Set<Watch>, maxWeight: Int, choice: Set<Watch>, priceSum: Int): Int =
            memoization(memo, generateKey(choice)) {
                priceAddingElement(memo, shop, choice, maxWeight, priceSum)}


        private fun memoization(memo: Memoizer, key: Int, f: () -> Int): Int =
            when (val w = memo[key]) {
                null -> f().also { memo[key] = it }
                else -> w
            }


        private fun generateKey(choice: Set<Watch>): Int =
//            choice.sortedBy { "${it.price}-${it.weight}" }.toString()
            choice.map{ it.hashCode() }.sorted().hashCode()
    }
}


data class Watch(val weight: Int, val price: Int)
