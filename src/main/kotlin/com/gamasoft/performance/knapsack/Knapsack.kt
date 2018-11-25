package com.gamasoft.performance.knapsack

import java.util.*;

class Knapsack {

    companion object {

        fun shop(vararg watches: Watch): Set<Watch> = watches.toHashSet()

        fun select(shop: Set<Watch>, maxWeight: Int): Int =
            select(KnapMemo(), shop, maxWeight, mutableSetOf())


        private fun select(memo: KnapMemo, shop: Set<Watch>, maxWeight: Int, choice: Set<Watch>): Int {
            val key = choice.toString();

            val best = memo.best(key, maxWeight);
            if (best > -1)
                return best;


            var maxVal = choice.sumBy { it.price }.or(0);

            for (w: Watch in shop) {
                if (!choice.contains(w) && w.weight <= maxWeight) {
                    val nc = HashSet(choice)
                    nc.add(w)
                    val value = select(memo, shop, maxWeight - w.weight, nc)
                    if (value > maxVal)
                        maxVal = value
                }
            }

            memo.storeBest(key, maxWeight, maxVal);
            return maxVal;
        }

    }
}



data class Watch (val weight: Int, val price: Int)


class KnapMemo: HashMap<String, SortedMap<Int, Int>>(){

    fun best(key: String, maxWeight: Int): Int {
        if (!containsKey(key))
            return -1

        val solutions = get(key)!!

        if (solutions.containsKey(maxWeight))
            return solutions.get(maxWeight)!!

        val hm = solutions.headMap(maxWeight)
        if (hm.isEmpty())
            return -1
        return solutions.get(hm.lastKey())!!

    }

    fun storeBest(key: String, maxWeight: Int, value: Int) {
        val solutions = getOrDefault(key, TreeMap())

        solutions.entries.removeIf{it.key >= maxWeight && it.value <= value}

        solutions.put(maxWeight, value)
        put(key, solutions);
    }
}

