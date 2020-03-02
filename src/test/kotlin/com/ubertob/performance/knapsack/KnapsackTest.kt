package com.ubertob.performance.knapsack

import com.ubertob.performance.knapsack.Knapsack.Companion.selectWatches
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class KnapsackTest {

    @Test
    fun singleShop() {

        val shop = Knapsack.shop(
            Watch(weight = 1, price = 1)
        )

        assertEquals(1, selectWatches(shop, maxWeight = 1))
        assertEquals(1, selectWatches(shop, maxWeight = 2))
    }

    @Test
    fun smallShop() {

        val shop = Knapsack.shop(
            Watch(weight = 1, price = 1),
            Watch(weight = 3, price = 2),
            Watch(weight = 1, price = 3)
        )

        assertEquals(3, selectWatches(shop, maxWeight = 1))
        assertEquals(4, selectWatches(shop, maxWeight = 2))
        assertEquals(4, selectWatches(shop, maxWeight = 3))
        assertEquals(5, selectWatches(shop, maxWeight = 4))
        assertEquals(6, selectWatches(shop, maxWeight = 5))

    }


    @Test
    fun mediumShop() {

        val shop = Knapsack.shop(
            Watch(1, 1),
            Watch(3, 2),
            Watch(5, 3),
            Watch(2, 4),
            Watch(4, 5),
            Watch(7, 6),
            Watch(6, 7),
            Watch(8, 8),
            Watch(9, 9)
        )

        assertAll(
            { assertEquals(1, selectWatches(shop, 1)) },
            { assertEquals(4, selectWatches(shop, 2)) },
            { assertEquals(5, selectWatches(shop, 3)) },
            { assertEquals(5, selectWatches(shop, 4)) },
            { assertEquals(6, selectWatches(shop, 5)) }
        )
    }

    @Test
    fun testPerformance() {

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

        (1..20).forEach {
            val start = System.currentTimeMillis()
            assertEquals(0, selectWatches(shop, 5))
            assertEquals(40, selectWatches(shop, 10))
            assertEquals(260, selectWatches(shop, 50))
            assertEquals(515, selectWatches(shop, 100))
            assertEquals(1199, selectWatches(shop, 250))
            assertEquals(1919, selectWatches(shop, 500))

            // -Xms6g -Xmx6g -Dgraal.ShowConfiguration=info -XX:+AlwaysPreTouch -XX:+UnlockExperimentalVMOptions -XX:-UseJVMCICompiler
            // 592 ms.

            //Graal CE 20.0 J11
            // -Xms6g -Xmx6g -Dgraal.ShowConfiguration=info -XX:+AlwaysPreTouch -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
            // 522 ms.

            //Graal EE 20.0 J11
            // -Xms6g -Xmx6g -Dgraal.ShowConfiguration=info -XX:+AlwaysPreTouch -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler
            // 448 ms.

            val elapsed = System.currentTimeMillis() - start
            println("knapsack " + elapsed)
        }
    }


    @Test
    fun bigShop() {

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
            Watch(20, 99),
            Watch(62, 247),
            Watch(57, 197),
            Watch(42, 67),
            Watch(77, 152),
            Watch(87, 277),
            Watch(27, 157),
            Watch(32, 122),
            Watch(67, 322),
            Watch(77, 77),
            Watch(12, 42),
            Watch(97, 202),
            Watch(52, 102),
            Watch(42, 222)
        )

        assertEquals(0, selectWatches(shop, 5))
        assertEquals(40, selectWatches(shop, 10))
        assertEquals(706, selectWatches(shop, 130))
        assertEquals(1438, selectWatches(shop, 279))
        assertEquals(1537, selectWatches(shop, 300))

    }

}
