package com.ubertob.performance.knapsack

typealias Memoizer = MutableMap<String, Int>

class Knapsack {

    companion object {

        fun shop(vararg watches: Watch): Set<Watch> = watches.toHashSet()

        fun selectWatches(shop: Set<Watch>, maxWeight: Int): Int {

            val memo = mutableMapOf<String, Int>()
            val watches = shop.asSequence()
            val top = selectWatchesMemo(memo, watches, maxWeight, emptySet(), 0)

            return top
        }

        fun priceAddingElement(memo: Memoizer, shop: Sequence<Watch>, choice: Set<Watch>, maxWeight: Int, priceSum: Int): Int =
            shop.filter { !(it in choice) && it.weight <= maxWeight }
                .map {
                    selectWatchesMemo(
                        memo,
                        shop,
                        maxWeight - it.weight,
                        choice + it,
                        priceSum + it.price) }
                .filter { it > priceSum }
                .max() ?: priceSum


        fun selectWatchesMemo(memo: Memoizer, shop: Sequence<Watch>, maxWeight: Int, choice: Set<Watch>, priceSum: Int): Int =
            memoization(memo, generateKey(choice)) {
                priceAddingElement(memo, shop, choice, maxWeight, priceSum)
            }


        private fun memoization(memo: Memoizer, key: String, f: () -> Int): Int =
            when (val w = memo[key]) {
                null -> f().also { memo[key] = it }
                else -> w
            }


        private fun generateKey(choice: Set<Watch>): String =
            choice.map{ it.uniqueKey() }.sorted().joinToString("")
    }
}


data class Watch(val weight: Int, val price: Int) {
    fun uniqueKey(): Int {
        return weight shl 16 + price
    }
}
