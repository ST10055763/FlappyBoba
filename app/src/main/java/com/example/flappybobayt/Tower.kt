package com.example.flappybobayt

class Tower(
    var x: Int,
    var y: Int,
    var width: Int,
    var height: Int,
    var velocity: Int
) {
    fun move() {
        x -= velocity
    }

    fun isOffScreen(): Boolean {
        return x + width < 0
    }
}
