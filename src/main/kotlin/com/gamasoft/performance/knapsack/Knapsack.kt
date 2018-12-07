package com.gamasoft.performance.knapsack

typealias Memoizer = MutableMap<String, Int>

class Knapsack {

    companion object {

        fun shop(vararg watches: Watch): Set<Watch> = watches.toHashSet()

        fun selectWatches(shop: Set<Watch>, maxWeight: Int): Int {

            val memo = mutableMapOf<String, Int>()
            val top = selectWatches(memo, shop, maxWeight, emptySet(), 0)
//            println(" ${memo.size}")

            return top
        }

        fun priceAddingElement(memo: Memoizer, shop: Set<Watch>, choice: Set<Watch>, maxWeight: Int, priceSum: Int): Int =
            shop.filter { !(it in choice) && it.weight <= maxWeight }
                .map {
                    selectWatches(
                        memo,
                        shop,
                        maxWeight - it.weight,
                        choice + it,
                        priceSum + it.price) }
                .filter { it > priceSum }
                .max() ?: priceSum


        fun selectWatches(memo: Memoizer, shop: Set<Watch>, maxWeight: Int, choice: Set<Watch>, priceSum: Int): Int =
            memoization(memo, generateKey(choice)) {
                priceAddingElement(memo, shop, choice, maxWeight, priceSum)}


        private fun memoization(memo: Memoizer, key: String, f: () -> Int): Int =
            when (val w = memo[key]) {
                null -> f().also { memo[key] = it }
                else -> w
            }


        private fun generateKey(choice: Set<Watch>): String =
//            choice.sortedBy { "${it.price}-${it.weight}" }.toString()
            choice.map{ it.hashCode() }.sorted().joinToString("")
    }
}


data class Watch(val weight: Int, val price: Int) {
    override fun hashCode(): Int {
        return weight * 1000 + price
    }
}
