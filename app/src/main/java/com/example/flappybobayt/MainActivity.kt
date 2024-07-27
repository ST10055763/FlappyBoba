package com.example.flappybobayt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        AppConstants.init(this.applicationContext)

        // Set up the play button
        val playButton: FloatingActionButton = findViewById(R.id.playButton)
        playButton.setOnClickListener {
            Toast.makeText(this, "Play!", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}