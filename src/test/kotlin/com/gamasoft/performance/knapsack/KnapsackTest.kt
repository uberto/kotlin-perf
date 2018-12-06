package com.gamasoft.performance.knapsack

import com.gamasoft.performance.knapsack.Knapsack.Companion.selectWatch
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class KnapsackTest {

    @Test
    fun smallShop() {

        val shop = Knapsack.shop(
            Watch(1, 1),
            Watch(3, 2),
            Watch(1, 3)
        )

        assertEquals(3, selectWatch(shop, 1))
        assertEquals(4, selectWatch(shop, 2))
        assertEquals(4, selectWatch(shop, 3))
        assertEquals(5, selectWatch(shop, 4))
        assertEquals(6, selectWatch(shop, 5))

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
            { assertEquals(1, selectWatch(shop, 1)) },
            { assertEquals(4, selectWatch(shop, 2)) },
            { assertEquals(5, selectWatch(shop, 3)) },
            { assertEquals(5, selectWatch(shop, 4)) },
            { assertEquals(6, selectWatch(shop, 5)) }
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

        (1..100).forEach {
            val start = System.currentTimeMillis()
            assertEquals(0, selectWatch(shop, 5))
            assertEquals(40, selectWatch(shop, 10))
            assertEquals(260, selectWatch(shop, 50))
            assertEquals(515, selectWatch(shop, 100))
            assertEquals(1199, selectWatch(shop, 250)) //c2 6187 graal 5844
            assertEquals(1919, selectWatch(shop, 500))
            //old
            //1125 -Xms6g -Xmx6g -XX:+UseParallelGC
            //1704 -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler

            //521 -Xms6g -Xmx6g -XX:+UseParallelGC
            //571 -Xms6g -Xmx6g -XX:+UseParallelGC -XX:+UnlockExperimentalVMOptions -XX:+UseJVMCICompiler

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

        assertEquals(0, selectWatch(shop, 5))
        assertEquals(40, selectWatch(shop, 10))
        assertEquals(706, selectWatch(shop, 130))
        assertEquals(1438, selectWatch(shop, 279))

    }

}
