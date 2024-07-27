package com.example.flappybobayt

import android.graphics.Canvas
import android.os.SystemClock
import android.view.SurfaceHolder

class GameThread(private val surfaceHolder: SurfaceHolder) : Thread() {

    private var isRunning = true
    private var startTime: Long = 0
    private var loopTime: Long = 0
    private val DELAY: Long = 33

    override fun run() {
        while (isRunning) {
            startTime = SystemClock.uptimeMillis()

            val canvas = surfaceHolder.lockCanvas(null)
            if (canvas != null) {
                synchronized(surfaceHolder) {
                    AppConstants.gameEngine.updateAndDrawBackgroundImage(canvas)
                    AppConstants.gameEngine.updateAndDrawBoba(canvas)
                    AppConstants.gameEngine.updateAndDrawTowers(canvas)
                    AppConstants.gameEngine.updateAndDrawScore(canvas)
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }

            loopTime = SystemClock.uptimeMillis() - startTime

            if (loopTime < DELAY) {
                try {
                    Thread.sleep(DELAY - loopTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }

    fun isRunning(): Boolean {
        return isRunning
    }

    fun setRunning(state: Boolean) {
        isRunning = state
    }
}
