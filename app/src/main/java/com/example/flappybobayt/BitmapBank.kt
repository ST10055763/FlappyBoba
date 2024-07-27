package com.example.flappybobayt

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.content.res.Resources

class BitmapBank(private val resources: Resources) {

    private var backgroundGame: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.background_game)

    private var boba: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.rsz_boba)

    init {
        backgroundGame = scaleImage(backgroundGame)
    }

    // return boba bitmap
    fun getBoba(currentFrame: Int): Bitmap {
        return boba
    }

    fun getBobaWidth(): Int {
        return  boba.width
    }

    fun getBobaHeight(): Int {
        return boba.height
    }

    // return game background (bitmap)
    fun getBackgroundGame(): Bitmap {
        return backgroundGame
    }

    // return width of background
    fun getBackgroundWidth(): Int {
        return backgroundGame.width
    }

    // return height of background
    fun getBackgroundHeight(): Int {
        return backgroundGame.height
    }

    private fun scaleImage(bitmap: Bitmap): Bitmap {
        val widthHeightRatio = getBackgroundWidth().toFloat() / getBackgroundHeight()

        // call createScaledBitmap() = new bitmap from scaled bitmap when possible
        val backgroundScaleWidth = (widthHeightRatio * AppConstants.SCREEN_HEIGHT).toInt()
        return Bitmap.createScaledBitmap(bitmap, backgroundScaleWidth, AppConstants.SCREEN_HEIGHT, false)
    }
}