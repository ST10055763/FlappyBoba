package com.example.flappybobayt

import android.content.Context
import android.graphics.Canvas
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView

class GameView(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    private lateinit var gameThread: GameThread

    init {
        holder.addCallback(this)
        initView()
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (!gameThread.isRunning()) {
            gameThread = GameThread(holder)
            gameThread.start()
        } else {
            gameThread.start()
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (gameThread.isRunning()) {
            gameThread.setRunning(false)
            var retry = true

            while (retry) {
                try {
                    gameThread.join()
                    retry = false
                } catch (e: InterruptedException) {
                }
            }
        }
    }

    fun initView() {
        holder.addCallback(this)
        isFocusable = true

        gameThread = GameThread(holder)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val action = event?.action

        if (action == MotionEvent.ACTION_DOWN) {
            AppConstants.gameEngine.gameState = 1
            AppConstants.gameEngine.boba.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED)
        }
        return true
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        AppConstants.gameEngine.updateAndDrawBackgroundImage(canvas)
        AppConstants.gameEngine.updateAndDrawBoba(canvas)
        AppConstants.gameEngine.updateAndDrawTowers(canvas)
        AppConstants.gameEngine.updateAndDrawScore(canvas)
    }
}
