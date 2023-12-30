package com.alicimsamil.starproject.skywebview.model

/**
 * The enum class represents the color variations of stars and includes three constant values: Red, Blue, Green, Yellow, Purple and Gray.
 */
enum class StarColor(val value: String) {
    Red("Red"), Blue("Blue"), Green("Green"), Yellow("Yellow"), Purple("Purple"), Gray("Gray")
}

/**
 * Function that returns a random color based on the specified star size.
 *
 * @param size Enum value representing the size of the star (StarSize.Small or StarSize.Big).
 * @return A randomly selected color (StarColor enum value).
 * For small stars, the color is randomly chosen from red, blue, or green.
 * For large stars, the color is randomly chosen from yellow, purple, or gray.
 */
fun getRandomColor(size: StarSize) : StarColor {
    return if (size == StarSize.Small)
        arrayOf(StarColor.Red, StarColor.Blue, StarColor.Green).random()
    else
        arrayOf(StarColor.Yellow, StarColor.Purple, StarColor.Gray).random()
}