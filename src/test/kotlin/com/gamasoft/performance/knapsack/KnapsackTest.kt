package com.gamasoft.performance.knapsack

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class KnapsackTest {

    @Test
    fun smallShop() {

        val shop = Knapsack.shop(
            newWatch(1, 1),
            newWatch(3, 2),
            newWatch(1, 3))

        assertEquals(3, Knapsack.select(shop, 1))
        assertEquals(4, Knapsack.select(shop, 2))
        assertEquals(4, Knapsack.select(shop, 3))
        assertEquals(5, Knapsack.select(shop, 4))
        assertEquals(6, Knapsack.select(shop, 5))

    }


    @Test
    fun mediumShop() {

        val shop = Knapsack.shop(
            newWatch(1, 1),
            newWatch(3, 2),
            newWatch(5, 3),
            newWatch(2, 4),
            newWatch(4, 5),
            newWatch(7, 6),
            newWatch(6, 7),
            newWatch(8, 8),
            newWatch(9, 9)
        )

        assertAll(
            {
                assertEquals(1, Knapsack.select(shop, 1))
            }, {
                assertEquals(4, Knapsack.select(shop, 2))
            }, {
                assertEquals(5, Knapsack.select(shop, 3))
            }, {
                assertEquals(5, Knapsack.select(shop, 4))
            }, {
                assertEquals(6, Knapsack.select(shop, 5))
            }

        )
    }

    @Test
    fun notSoBigShop() {

        val shop = Knapsack.shop(
            Watch(20, 65),
            Watch(8, 35),
            Watch(60, 245),
            Watch(55, 195),
            Watch(40, 65),
            Watch(70, 150),
            Watch(85, 275),
            Watch(25, 155),
            Watch(30, 120),
            Watch(65, 320),
            Watch(75, 75),
            Watch(10, 40),
            Watch(95, 200),
            Watch(50, 100),
            Watch(40, 220),
            Watch(20, 99)
        )

        assertEquals(0, Knapsack.select(shop, 5))
        assertEquals(40, Knapsack.select(shop, 10))

        assertEquals(695, Knapsack.select(shop, 130))

        val start = System.currentTimeMillis()
        assertEquals(1919, Knapsack.select(shop, 500))
        // 3743 ms Java,
        // 2681 (1.8)
        // 2524 (1.6)
        // 3630 (1.8 Graal)
        // 3100 (1.8 Graal + parOld)
        // 2462 (1.8 parOld)
        // 2574 (1.6 parOld)


        println("klapsack " + (System.currentTimeMillis() - start))

    }


    @Test
    fun bigShop() {

        val shop = Knapsack.shop(
            newWatch(20, 65),
            newWatch(8, 35),
            newWatch(60, 245),
            newWatch(55, 195),
            newWatch(40, 65),
            newWatch(70, 150),
            newWatch(85, 275),
            newWatch(25, 155),
            newWatch(30, 120),
            newWatch(65, 320),
            newWatch(75, 75),
            newWatch(10, 40),
            newWatch(95, 200),
            newWatch(50, 100),
            newWatch(40, 220),
            newWatch(20, 99),
            newWatch(62, 247),
            newWatch(57, 197),
            newWatch(42, 67),
            newWatch(77, 152),
            newWatch(87, 277),
            newWatch(27, 157),
            newWatch(32, 122),
            newWatch(67, 322),
            newWatch(77, 77),
            newWatch(12, 42),
            newWatch(97, 202),
            newWatch(52, 102),
            newWatch(42, 222)
        )

        assertEquals(0, Knapsack.select(shop, 5))
        assertEquals(40, Knapsack.select(shop, 10))

        assertEquals(706, Knapsack.select(shop, 130))

        assertEquals(1438, Knapsack.select(shop, 279))

//        val start = System.currentTimeMillis()
//        assertEquals(1699, Knapsack.select(shop, 342))
//
//        println("klapsack " + (System.currentTimeMillis() - start)) //67725

    }

    private fun newWatch(weight: Int, price: Int): Watch {
        return Watch(weight, price)
    }
}
