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
    lateinit var bitmap: Bitmap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val image = intent.getStringExtra("image")
        Toast.makeText(applicationContext, " " + image, Toast.LENGTH_SHORT).show()
        fotografia = findViewById(R.id.fotografia)
       // fotografia2 = findViewById(R.id.fotografia2)
        if (image != null) {
            fotografia.setImageURI(image.toUri())
            bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            fotografia.setImageBitmap(bitmap)
        }
        //efecto()
    }

    /*private fun efecto(){
        //sepia.setOnClickListener(){
            var cambio = grayscale(bitmap)
            fotografia.setImageBitmap(cambio)
        }
    }
    fun grayscale(src: Bitmap): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)
        // constant grayscale
        val GS_RED = 0.299
        val GS_GREEN = 0.587
        val GS_BLUE = 0.114
        // color information
        var A: Int
        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                // get color on each channel
                A = Color.alpha(pixel)
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)
                // apply grayscale sample
                R = (GS_RED * R + GS_GREEN * G + GS_BLUE * B).toInt()
                G = R
                B = G

                // apply intensity level for sepid-toning on each channel
                R += 110
                if (R > 255) {
                    R = 255
                }
                G += 65
                if (G > 255) {
                    G = 255
                }
                B += 20
                if (B > 255) {
                    B = 255
                }

                // set new pixel color to output image
                bmOut.setPixel(x, y, Color.argb(A, R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }*/
}