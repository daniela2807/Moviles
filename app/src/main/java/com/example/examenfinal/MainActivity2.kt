package com.example.examenfinal

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri


class MainActivity2 : AppCompatActivity() {

    lateinit var fotografia: ImageView
   lateinit var sepia: Button
    lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val image = intent.getStringExtra("image")
        Toast.makeText(applicationContext, " " + image, Toast.LENGTH_SHORT).show()
        fotografia = findViewById(R.id.fotografia)
        sepia = findViewById(R.id.button)
       // fotografia2 = findViewById(R.id.fotografia2)
        if (image != null) {
            fotografia.setImageURI(image.toUri())
            bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            fotografia.setImageBitmap(bitmap)
        }
        //efecto()
    }

}