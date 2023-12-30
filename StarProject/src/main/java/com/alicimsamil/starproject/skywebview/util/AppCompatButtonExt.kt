package com.alicimsamil.starproject.skywebview.util

import android.graphics.Color
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet

/**
 * This function is used to set layout parameters for an AppCompatButton.
 *
 * @param startStart An integer resource value specifying the start point of the button (default is -1).
 * @param endEnd An integer resource value specifying the end point of the button (default is -1).
 * @param startEnd An integer resource value specifying both the start and end of the button (default is -1).
 * @param endStart An integer resource value specifying both the end and start of the button (default is -1).
 * @param marginLeft An integer value specifying the margin from the left edge of the button (default is 0).
 * @param marginRight An integer value specifying the margin from the right edge of the button (default is 0).
 */
fun AppCompatButton.setCustomLayoutParams(
    startStart: Int = -1,
    endEnd: Int = -1,
    startEnd: Int = -1,
    endStart: Int = -1,
    marginLeft: Int = 0,
    marginRight: Int = 0
){
    val buttonParams = ConstraintLayout.LayoutParams(0, ConstraintLayout.LayoutParams.WRAP_CONTENT)
    buttonParams.apply {
        horizontalWeight = 1f
        startToStart = startStart
        endToEnd = endEnd
        startToEnd = startEnd
        endToStart = endStart
        bottomToBottom = ConstraintSet.PARENT_ID
        setMargins(marginLeft, 0, marginRight, 10)
    }
    this.layoutParams = buttonParams
}

/**
 * This function is used to create and configure an AppCompatButton with specified text
 * and default styling properties. It generates a unique ID for the button, sets its text
 * from a string resource, sets a red background color, white text color, and disables
 * the all caps mode.
 *
 * @param textRes The string resource ID for the text to be displayed on the button.
 */
fun AppCompatButton.createButton(
    textRes: Int
){
    this.apply {
        id = ConstraintLayout.generateViewId()
        text = context.getText(textRes)
        setBackgroundColor(Color.RED)
        setTextColor(Color.WHITE)
        isAllCaps = false
    }
}