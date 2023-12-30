package com.alicimsamil.starproject.skywebview.manager

import android.util.Log
import androidx.annotation.VisibleForTesting
import com.alicimsamil.starproject.skywebview.model.Star
import com.alicimsamil.starproject.skywebview.model.StarBrightness
import com.alicimsamil.starproject.skywebview.model.StarSize
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Sky Manager that manages a celestial sky composed of stars.
 * The class provides functionalities to add stars, reset the sky, and monitors the fullness status of the sky.
 * Stars are added based on their sizes, and the current state of the sky is logged for observation.
 * The fullness status is tracked using StateFlow, allowing external components to observe changes.
 */
internal object SkyManager {
    private const val MAX_STARS = 10
    internal val stars = arrayListOf<Star>()
    internal val isSkyFull = MutableStateFlow(false)

    /**
     * Function used to add a star.
     * @param size: Size of the added star (utilizes StarSize enum).
     */
    internal fun addStar(size: StarSize) {
        if (checkStarListSize()){
            if (size == StarSize.Big){
                stars.add(Star(size = StarSize.Big))
            } else {
                stars.add(Star(size = StarSize.Small))
            }
        }
        logAllStars()
    }

    /**
     * Function used to reset the sky.
     */
    internal fun resetSky() {
        logAllStars()
        stars.clear()
    }

    /**
     * Function used to log all stars.
     */
    private fun logAllStars() {
        val concatenatedMessage = stars.joinToString(", ") { star ->
            "Star(Size: ${star.size.value}, Color: ${star.color.value}, Brightness: ${star.brightness.value})"
        }
        Log.d("Stars: ", concatenatedMessage)
        Log.d("Bright Stars: ", stars.count { star -> star.brightness == StarBrightness.Bright }.toString())
    }

    /**
     * Function used to check the list size and update StateFlow with the current status.
     * @return true if the sky is not full, false otherwise.
     */
    private fun checkStarListSize() : Boolean{
        val isSkyFullValue = stars.size < MAX_STARS
        isSkyFull.value = !isSkyFullValue
        return isSkyFullValue
    }

    /**
     * A utility function for testing purposes that validates the checkListSize function.
     * This function invokes the private checkListSize function and returns its result.
     */
    @VisibleForTesting(otherwise = VisibleForTesting.PRIVATE)
    internal fun validateCheckStarListSize() : Boolean{
        return checkStarListSize()
    }
}