# SkyWebView - Custom WebView

Custom view representing a sky with star and interactive buttons.

This custom view includes a WebView to display an image of the sky, buttons for adding stars, resetting the sky, and a listener to monitor the state of the sky's fullness.

## Features

- **Sky Manager:** Manages the state and operations related to the sky.
- **WebView:** Displays the image of the sky.
- **Small Star Button:** Adds small stars to the sky.
- **Big Star Button:** Adds big stars to the sky.
- **Reset Button:** Resets the state of the stars in the sky.

## Usage

1. Include the `SkyWebView` custom view in your layout XML file.

    ```xml
    <com.alicimsamil.starproject.skywebview.SkyWebView
        android:id="@+id/skyWebView"
        android:layout_width="match_parent"
        android:layout_height="match_parent" />

    ```

2. In your activity or fragment, find the view. (optional)

    ```kotlin
    var skyWebView = findViewById(R.id.skyWebView)

3. Integrate SkyWebView with the activity's lifecycle by incorporating the following code. This implementation will not only display a toast message, indicating that the sky is filled with stars, but also ensure that your current stars are saved when the app enters a terminated state. (optional)

    ```kotlin
    lifecycle.addObserver(skyWebView)

4. Add a star to the SkyWebView upon activity creation. (optional)

    ```kotlin
    skyWebView.addStarInterface(StarSize.Small)
    
## Media
### Screeshots
<img src="https://github.com/alicimsamil/StarProject/assets/81926983/702a11b0-f5ff-4e61-8e76-a34130d8a4cc" width="250" />
<img src="https://github.com/alicimsamil/StarProject/assets/81926983/603fd034-8ee8-4337-9f4e-38e789e6cdb7" width="250" />

### Log Output
<img src="https://github.com/alicimsamil/StarProject/assets/81926983/e99f61b6-9090-4635-966a-94763c47ea5f" />
