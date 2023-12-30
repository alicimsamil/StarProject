package com.alicimsamil.starproject.skywebview.model

/**
 * The Star class represents a celestial body and encapsulates properties such as its size, color, and brightness.
 * @param size Represents the size category of the star, specified through the StarSize enum.
 * @param color Represents the color variation of the star, defaults to a random color.
 * @param brightness Represents the brightness level of the star, defaults to a random brightness.
 */
data class Star(
    val size: StarSize,
    val color: StarColor = getRandomColor(size),
    val brightness: StarBrightness = getRandomBrightness()
)