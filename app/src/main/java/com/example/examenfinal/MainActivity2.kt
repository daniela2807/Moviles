package com.example.examenfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri

class MainActivity2 : AppCompatActivity() {

    lateinit var fotografia: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val image = intent.getStringExtra("image")
        Toast.makeText(applicationContext," "+image, Toast.LENGTH_SHORT).show()
        fotografia = findViewById(R.id.fotografia)
        if (image != null) {
            fotografia.setImageURI(image.toUri())
        }
    }
}