package com.example.examenfinal

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener


class MainActivity2 : AppCompatActivity() {

    lateinit var fotografia: ImageView
    lateinit var bitmap: Bitmap
    lateinit var cambio: Bitmap
    lateinit var spinner1 : Spinner
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val image = intent.getStringExtra("image")
        Toast.makeText(applicationContext, " " + image, Toast.LENGTH_SHORT).show()
        fotografia = findViewById(R.id.fotografia)
        spinner1 = findViewById(R.id.idBasicos)
        val filtrosB = resources.getStringArray(R.array.Fbasicos)
       // fotografia2 = findViewById(R.id.fotografia2)
        if (image != null) {
            fotografia.setImageURI(image.toUri())
            //bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            //fotografia.setImageBitmap(bitmap)
        }

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    var filtroSeleccionado = filtrosB[position]

                    if (filtroSeleccionado != "Filtros Basicos") {
                        if (image != null) {
                            fotografia.setImageURI(image.toUri())
                            //bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                            //fotografia.setImageBitmap(bitmap)
                        }
                        bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                        when(filtroSeleccionado) {
                            "Negativo" -> cambio = Negativo(bitmap)
                            "Escala de Grises" -> cambio = Grises(bitmap)
                            else-> {
                                cambio = bitmap
                            }
                        }
                        fotografia.setImageBitmap(cambio)
                    }
                }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        //efecto()
    }

    private fun efecto(){
        //sepia.setOnClickListener(){
            var cambio = Negativo(bitmap)
            fotografia.setImageBitmap(cambio)
        }
   private  fun Negativo(src: Bitmap): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

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
                R = 255 - Color.red(pixel)
                G = 255- Color.green(pixel)
                B = 255- Color.blue(pixel)

                bmOut.setPixel(x, y, Color.rgb( R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    private  fun Grises(src: Bitmap): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        var GS_RED = 0.299;
        var GS_GREEN = 0.587;
        var GS_BLUE = 0.114;
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
                R = Color.red(pixel)
                G = Color.green(pixel)
                B = Color.blue(pixel)

                B = (GS_RED * R + GS_GREEN * G + GS_BLUE * B).toInt();
                G= B
                R= G
                bmOut.setPixel(x, y, Color.rgb( R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }
}