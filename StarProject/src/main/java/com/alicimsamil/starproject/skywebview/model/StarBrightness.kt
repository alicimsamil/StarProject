package com.alicimsamil.starproject.skywebview.model

/**
 * This enum class represents the brightness levels of stars and includes two constant values: Bright and NotSoMuch.
 */
enum class StarBrightness(val value: String) {
    Bright("Bright"), NotSoMuch("Not so much")
}

/**
 * Returns a randomly selected brightness level for a star.
 *
 * @return A randomly selected brightness level (StarBrightness enum value).
 * The brightness can be either "Bright" or "Not so much".
 */
fun getRandomBrightness() : StarBrightness{
    return StarBrightness.values().random()
}