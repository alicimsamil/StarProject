package com.alicimsamil.starproject.skywebview.manager

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.annotation.VisibleForTesting
import com.alicimsamil.starproject.skywebview.model.Star
import com.alicimsamil.starproject.skywebview.model.StarBrightness
import com.alicimsamil.starproject.skywebview.model.StarSize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Sky Manager that manages a celestial sky composed of stars.
 * The class provides functionalities to add stars, reset the sky, and monitors the fullness status of the sky.
 * Stars are added based on their sizes, and the current state of the sky is logged for observation.
 * The fullness status is tracked using StateFlow, allowing external components to observe changes.
 */
internal object SkyManager {
    private const val MAX_STARS = 10
    internal var stars = arrayListOf<Star>()
    internal val isSkyFull = MutableStateFlow(false)
    private var sharedPref : SharedPreferences? = null
    private const val SAVED_STARS = "SAVED_STARS"
    private val gson = Gson()

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
        val isStarsNotEmpty = stars.isNotEmpty()
        val concatenatedMessage = stars.joinToString(", ") { star ->
            "Star(Size: ${star.size.value}, Color: ${star.color.value}, Brightness: ${star.brightness.value})"
        }
        Log.d("Stars: ", if (isStarsNotEmpty) concatenatedMessage else "List is empty.")
        Log.d("Bright Stars: ", if (isStarsNotEmpty) stars.count { star -> star.brightness == StarBrightness.Bright }.toString() else "List is empty.")
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

    /**
     * Initializes the shared preferences for the library.
     * @param context The context used to obtain the shared preferences.
     */
    internal fun initSharedPref(context: Context?){
        sharedPref = context?.getSharedPreferences(context.packageName ,Context.MODE_PRIVATE) ?: return
    }

    /**
     * Saves the 'stars' data to local storage using SharedPreferences.
     * Converts the 'stars' data to JSON format before saving.
     */
    internal fun saveStarsToLocal(){
        val starsJsonFormat = gson.toJson(stars)
        sharedPref?.edit()?.apply {
            putString(SAVED_STARS, starsJsonFormat)
            apply()
        }
    }

    /**
     * Retrieves the 'stars' data from local storage using SharedPreferences.
     * Converts the stored JSON-formatted data back into a list of 'Star' objects.
     */
    internal fun getStarsFromLocal(){
        val starsJsonFormat =sharedPref?.getString(SAVED_STARS, "")
        val type = object : TypeToken<ArrayList<Star>>() {}.type
        val starList : ArrayList<Star>? = gson.fromJson(starsJsonFormat, type)
        starList?.let {
            stars = starList
        }
    }
}