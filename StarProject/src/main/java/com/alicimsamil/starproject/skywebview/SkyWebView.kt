package com.alicimsamil.starproject.skywebview

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.webkit.WebView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.alicimsamil.starproject.R
import com.alicimsamil.starproject.skywebview.manager.SkyManager
import com.alicimsamil.starproject.skywebview.model.StarSize
import com.alicimsamil.starproject.skywebview.util.createButton
import com.alicimsamil.starproject.skywebview.util.setCustomLayoutParams
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * Custom view representing a sky with stars and interactive buttons.
 *
 * This view includes a WebView to display an image of the sky, buttons for adding stars,
 * resetting the sky, and a listener to monitor the state of the sky's fullness.
 *
 * @property skyManager Manages the state and operations related to the sky.
 * @property webView WebView for displaying the image of the sky.
 * @property smallStarButton Button for adding small stars to the sky.
 * @property bigStarButton Button for adding big stars to the sky.
 * @property resetButton Button for resetting the state of the stars in the sky.
 */
class SkyWebView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr), DefaultLifecycleObserver {

    private var skyManager = SkyManager
    private var webView: WebView
    private var smallStarButton: AppCompatButton
    private var bigStarButton: AppCompatButton
    private var resetButton: AppCompatButton

    init {
        webView = WebView(context)
        smallStarButton = AppCompatButton(context)
        bigStarButton = AppCompatButton(context)
        resetButton = AppCompatButton(context)
        setBackgroundColor(Color.BLACK)
        initViews()
        initListeners()
    }

    private fun initViews(){
        initWebView()
        initButtons()
    }

    /**
     * Initializes the click listeners for interactive buttons.
     * Associates click events for small star, big star, and reset buttons with corresponding actions.
     */
    private fun initListeners(){
        smallStarButton.setOnClickListener {
            addStarInterface(StarSize.Small)
        }
        bigStarButton.setOnClickListener {
            addStarInterface(StarSize.Big)
        }
        resetButton.setOnClickListener {
            resetStars()
        }
    }

    /**
    * Initializes the WebView for displaying an image from a specified URL.
    * Configures WebView settings, sets layout parameters, and loads the image URL.
    */
    private fun initWebView(){
        webView.id = generateViewId()
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.settings.useWideViewPort = true
        val webViewParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        webViewParams.topToTop = ConstraintSet.PARENT_ID
        webViewParams.bottomToBottom = ConstraintSet.PARENT_ID
        webView.layoutParams = webViewParams
        addView(webView)
        webView.loadUrl(IMAGE_URL)
    }

    /**
     * Initializes and configures the buttons for small star, big star, and reset actions.
     * Sets button labels and custom layout parameters.
     */
    private fun initButtons(){
        smallStarButton.createButton (R.string.small_star)
        bigStarButton.createButton (R.string.big_star)
        resetButton.createButton (R.string.reset)

        smallStarButton.setCustomLayoutParams(
            startStart = ConstraintSet.PARENT_ID,
            endStart = bigStarButton.id,
            marginLeft = 10
        )

        bigStarButton.setCustomLayoutParams(
            startEnd = smallStarButton.id,
            endStart = resetButton.id,
            marginLeft = 10
        )

        resetButton.setCustomLayoutParams(
            startEnd = bigStarButton.id,
            endEnd = ConstraintSet.PARENT_ID,
            marginLeft = 10,
            marginRight = 10
        )

        addView(smallStarButton)
        addView(bigStarButton)
        addView(resetButton)
    }

    /**
     * Initializes the listener for monitoring the state of the sky's fullness using a Flow.
     * Displays a toast message when the sky is full and resets the sky state.
     */
    private suspend fun initListener(){
        skyManager.isSkyFull.collectLatest { isFull ->
            if (isFull) {
                Toast.makeText(context, R.string.sky_is_full, Toast.LENGTH_LONG).show()
                skyManager.isSkyFull.value = false
            }
        }
    }

    /**
     * Resets the state of the stars in the sky using the SkyManager.
     */
    private fun resetStars(){
        skyManager.resetSky()
    }

    /**
     * Adds a star of the specified size to the sky using the SkyManager.
     * This function is called to place a star in the sky with the given size.
     * @param size The size of the star to be added (Small or Big).
     */
    fun addStarInterface(size: StarSize){
        skyManager.addStar(size)
    }

    /**
     * Initiates necessary operations, such as initializing shared preferences, retrieving stars from local storage,
     * and setting up a listener to monitor the sky's state asynchronously.
     */
    override fun onCreate(owner: LifecycleOwner)  {
        super.onStart(owner)
        skyManager.initSharedPref(context)
        skyManager.getStarsFromLocal()
        owner.lifecycleScope.launch {
            initListener()
        }
    }

    /**
     * Performs necessary operations, such as saving stars to local storage using 'skyManager'.
     */
    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        skyManager.saveStarsToLocal()
    }

    companion object {
        const val IMAGE_URL = "https://img.etimg.com/thumb/msid-72948091,width-650,imgsize-95069,resizemode-4,quality-100/star_istock.jpg"
    }

}