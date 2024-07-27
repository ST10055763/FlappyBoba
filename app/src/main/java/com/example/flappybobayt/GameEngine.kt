package com.example.flappybobayt

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

class GameEngine {

    private lateinit var backgroundImage: BackgroundImage
    lateinit var boba: Boba
    private val towers: MutableList<Tower> = mutableListOf()
    private var towerSpawnTime: Long = 0
    private var score: Int = 0
    private var startTime: Long = System.currentTimeMillis()
    private val paint = Paint()

    var gameState: Int = 0

    init {
        backgroundImage = BackgroundImage()
        boba = Boba()
        gameState = 0
    }

    fun updateAndDrawBackgroundImage(canvas: Canvas) {
        backgroundImage.setX(backgroundImage.getX() - backgroundImage.getVelocity())
        if (backgroundImage.getX() < -AppConstants.bitmapBank.getBackgroundWidth()) {
            backgroundImage.setX(0)
        }
        canvas.drawBitmap(AppConstants.bitmapBank.getBackgroundGame(), backgroundImage.getX().toFloat(), backgroundImage.getY().toFloat(), null)

        if (backgroundImage.getX() < -AppConstants.SCREEN_WIDTH) {
            canvas.drawBitmap(AppConstants.bitmapBank.getBackgroundGame(), (backgroundImage.getX() + AppConstants.bitmapBank.getBackgroundWidth()).toFloat(), backgroundImage.getY().toFloat(), null)
        }
    }

    fun updateAndDrawBoba(canvas: Canvas) {
        if (gameState == 1 ) {
            if (boba.getY() < (AppConstants.SCREEN_HEIGHT - AppConstants.bitmapBank.getBobaHeight())
                || boba.getVelocity() < 0) {
                boba.setVelocity(boba.getVelocity() + AppConstants.gravity)
                boba.setY(boba.getY() + boba.getVelocity())
            }
        }

        val currentFrame = boba.getCurrentFrame()
        canvas.drawBitmap(AppConstants.bitmapBank.getBoba(currentFrame), boba.getX().toFloat(), boba.getY().toFloat(), null)
        boba.setCurrentFrame(currentFrame)
    }

    fun updateAndDrawTowers(canvas: Canvas) {
        if (gameState == 1) {
            // Spawn towers
            if (System.currentTimeMillis() - towerSpawnTime > 2000) { // Spawn tower every 2 seconds
                val towerWidth = 200
                val towerHeight = AppConstants.SCREEN_HEIGHT
                val gap = 550
                val randomY = (Math.random() * (AppConstants.SCREEN_HEIGHT - gap)).toInt() + gap / 2

                // Bottom tower
                towers.add(Tower(AppConstants.SCREEN_WIDTH, randomY, towerWidth, towerHeight - randomY, 10))
                // Top tower
                towers.add(Tower(AppConstants.SCREEN_WIDTH, 0, towerWidth, randomY - gap, 10))

                towerSpawnTime = System.currentTimeMillis()
            }

            // Move and draw towers
            val iterator = towers.iterator()
            while (iterator.hasNext()) {
                val tower = iterator.next()
                tower.move()
                canvas.drawRect(tower.x.toFloat(), tower.y.toFloat(), (tower.x + tower.width).toFloat(), (tower.y + tower.height).toFloat(), paint)

                // Remove off-screen towers
                if (tower.isOffScreen()) {
                    iterator.remove()
                    score += 1
                }
            }
        }
    }

    fun updateAndDrawScore(canvas: Canvas) {
        val elapsedTime = (System.currentTimeMillis() - startTime) / 1000
        val minutes = elapsedTime / 60
        val seconds = elapsedTime % 60
        val timeString = String.format("%d:%02d", minutes, seconds)

        paint.color = Color.WHITE
        paint.textSize = 64f
        canvas.drawText("Score: $score", 50f, 100f, paint)
        canvas.drawText("Time: $timeString", 50f, 200f, paint)
    }
}
