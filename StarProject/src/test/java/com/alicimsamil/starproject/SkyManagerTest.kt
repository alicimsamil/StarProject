package com.alicimsamil.starproject

import com.alicimsamil.starproject.skywebview.manager.SkyManager
import com.alicimsamil.starproject.skywebview.model.StarSize
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import org.junit.Before
import org.junit.Test

class SkyManagerTest {

    private lateinit var skyManager: SkyManager

    @Before
    fun setup() {
        skyManager = SkyManager
    }

    @Test
    fun test_add_small_star() {
        skyManager.stars.clear()
        assertEquals(skyManager.stars.size, 0)
        skyManager.addStar(StarSize.Small)
        assertEquals(skyManager.stars[0].size, StarSize.Small)
    }

    @Test
    fun test_add_big_star() {
        skyManager.stars.clear()
        assertEquals(skyManager.stars.size, 0)
        skyManager.addStar(StarSize.Big)
        assertEquals(skyManager.stars[0].size, StarSize.Big)
    }

    @Test
    fun test_reset_sky() {
        skyManager.stars.clear()
        repeat(5) {
            skyManager.addStar(StarSize.Big)
        }
        assertEquals(skyManager.stars.size, 5)
        skyManager.resetSky()
        assertEquals(skyManager.stars.size, 0)
    }

    @Test
    fun test_check_list_size_under_10() {
        skyManager.stars.clear()
        repeat(5) {
            skyManager.addStar(StarSize.Big)
        }
        assertTrue(skyManager.validateCheckStarListSize())
    }

    @Test
    fun test_check_list_size_over_10() {
        skyManager.stars.clear()
        repeat(12) {
            skyManager.addStar(StarSize.Big)
        }
        assertFalse(skyManager.validateCheckStarListSize())
    }

    @Test
    fun test_skyIsFull_state_is_true() {
        skyManager.stars.clear()
        repeat(12) {
            skyManager.addStar(StarSize.Big)
        }
        skyManager.validateCheckStarListSize()
        assertTrue(skyManager.isSkyFull.value)
    }

    @Test
    fun test_skyIsFull_state_is_false() {
        skyManager.stars.clear()
        repeat(1) {
            skyManager.addStar(StarSize.Big)
        }
        skyManager.validateCheckStarListSize()
        assertFalse(skyManager.isSkyFull.value)
    }
}