package com.example.flappybobayt

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager

object AppConstants {

    // bitmap obj ref
    lateinit var bitmapBank: BitmapBank

    // game engine ref
    lateinit var gameEngine: GameEngine

    var SCREEN_WIDTH: Int = 0
    var SCREEN_HEIGHT: Int = 0

    var gravity: Int = 0

    var VELOCITY_WHEN_JUMPED = 0

    fun init(context: Context) {
        setScreenSize(context)
        bitmapBank = BitmapBank(context.resources)
        gameEngine = GameEngine()

        AppConstants.gravity = 3;
        AppConstants.VELOCITY_WHEN_JUMPED = -40
    }

    private fun setScreenSize(context: Context) {
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display = wm.defaultDisplay
        val metrics = DisplayMetrics()
        display.getMetrics(metrics)
        SCREEN_WIDTH = metrics.widthPixels
        SCREEN_HEIGHT = metrics.heightPixels
    }
}