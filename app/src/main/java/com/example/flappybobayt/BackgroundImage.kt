package com.example.flappybobayt

class BackgroundImage {

    private var backgroundImageX: Int = 0
    private var backgroundImageY: Int = 0
    private var backgroundVelocity: Int = 2

    // get x coord
    fun getX(): Int {
        return backgroundImageX
    }

    // get y coord
    fun getY(): Int {
        return backgroundImageY
    }

    // set x coord
    fun setX(backgroundImageX: Int) {
        this.backgroundImageX = backgroundImageX
    }

    // set y coord
    fun setY(backgroundImageY: Int) {
        this.backgroundImageY = backgroundImageY
    }

    // get velocity
    fun getVelocity(): Int {
        return backgroundVelocity
    }
}