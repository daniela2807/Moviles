package com.example.examenfinal

import android.R.attr
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.isVisible
import java.lang.Integer.parseInt


class MainActivity2 : AppCompatActivity() {

    lateinit var fotografia: ImageView
    lateinit var bitmap: Bitmap
    lateinit var cambio: Bitmap
    lateinit var spinner1 : Spinner
    lateinit var spinner2: Spinner
    lateinit var brillo : TextView
    lateinit var gamma : TextView
    lateinit var Contraste : TextView
    lateinit var filtro: RadioGroup
    lateinit var contentBrillo : LinearLayout
    lateinit var contentContraste : LinearLayout
    lateinit var contentGamma : LinearLayout
    lateinit var contentFiltro : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        //filtro.clearCheck()
        val image = intent.getStringExtra("image")
        Toast.makeText(applicationContext, " " + image, Toast.LENGTH_SHORT).show()
        fotografia = findViewById(R.id.fotografia)
        spinner1 = findViewById(R.id.idBasicos)
        spinner2 = findViewById(R.id.idConvolucion)
        brillo = findViewById(R.id.Brillo)
        Contraste = findViewById(R.id.Contraste)
        filtro = findViewById(R.id.Filtro)
        gamma = findViewById(R.id.Gamma)
        contentBrillo = findViewById(R.id.PanelBrillo)
        contentFiltro = findViewById(R.id.PanelFiltro)
        contentContraste = findViewById(R.id.PanelContraste)
        contentGamma = findViewById(R.id.PanelGamma)
        val filtrosB = resources.getStringArray(R.array.Fbasicos)
        val filtrosC = resources.getStringArray(R.array.Fconvolucion)
       // fotografia2 = findViewById(R.id.fotografia2)
        if (image != null) {
            fotografia.setImageURI(image.toUri())
            //bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            //fotografia.setImageBitmap(bitmap)
        }

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    var filtroSeleccionado = filtrosB[position]
                    contentFiltro.isVisible = false;
                    contentGamma.isVisible = false;
                    contentBrillo.isVisible = false;
                    contentContraste.isVisible = false;
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
                            "Brillo" -> {
                                contentBrillo.isVisible = true
                                cambio = bitmap
                            }
                            "Contraste" -> {
                                contentContraste.isVisible = true
                                cambio = bitmap
                            }
                            "Gamma" -> {
                                contentGamma.isVisible = true
                                cambio = bitmap
                            }
                            "Separacion de Canales" -> {
                                contentFiltro.isVisible = true
                                cambio = bitmap
                            }
                            else-> {
                                cambio = bitmap
                            }
                        }
                        //contentBrillo.isVisible = false;
                        fotografia.setImageBitmap(cambio)
                    }
                }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var filtroSeleccionado = filtrosC[position]
                if (filtroSeleccionado != "Filtros de Convolucion") {
                    if (image != null) {
                        fotografia.setImageURI(image.toUri())
                        //bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                        //fotografia.setImageBitmap(bitmap)
                    }
                    bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                    when(filtroSeleccionado) {
                        "Gaussian Blur" -> cambio = GaussianBlur(bitmap)
                        else-> {
                            cambio = bitmap
                        }
                    }
                    //contentBrillo.isVisible = false;
                    fotografia.setImageBitmap(cambio)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        brillo.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (brillo.text != null) {
                    cambio = Brillo(bitmap, parseInt(brillo.text.toString()))
                } else {
                    //Default
                    cambio = Brillo(bitmap, 50)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        Contraste.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (Contraste.text != null) {
                    cambio = Contraste(bitmap, parseInt(Contraste.text.toString()))
                } else {
                    //Default
                    cambio = Contraste(bitmap, 50)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        gamma.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (gamma.text != null) {
                    cambio = Gamma(bitmap, (gamma.text.toString()).toDouble())
                } else {
                    //Default
                    cambio = Gamma(bitmap, 5.0)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        filtro.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            fotografia.setImageURI(image?.toUri())
            bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            if (i == R.id.Rojo) {
                cambio = Filtro(bitmap, 1)
            } else if (i == R.id.Verde) {
                cambio = Filtro(bitmap, 2)
            } else {
                cambio = Filtro(bitmap, 3)
            }
            fotografia.setImageBitmap(cambio)
        })
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

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    private  fun Brillo(src: Bitmap, num: Int): Bitmap {
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
                R = Color.red(pixel)
                G =Color.green(pixel)
                B = Color.blue(pixel)

                R += num;
                if(R > 255) { R = 255; }
                else if(R < 0) { R = 0; }

                G += num;
                if(G > 255) { G = 255; }
                else if(G < 0) { G = 0; }

                B += num;
                if(B > 255) { B = 255; }
                else if(B < 0) { B = 0; }

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    private  fun Filtro(src: Bitmap, num: Int): Bitmap {
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
                R = Color.red(pixel)
                G =Color.green(pixel)
                B = Color.blue(pixel)

                if(num == 1){
                    R= R
                    G = -255
                    B = -255
                    bmOut.setPixel(x, y, Color.rgb(R, G, B))
                }else if (num == 2){
                    R = -255
                    G=G
                    B = -255
                    bmOut.setPixel(x, y, Color.rgb(R, G, B))
                }else{
                    R = -255
                    G = -255
                    B=B
                    bmOut.setPixel(x, y, Color.rgb(R, G, B))
                }
            }
        }
        src.recycle()
        return bmOut
    }

    private fun Gamma(src: Bitmap, num: Double): Bitmap {

        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        val MAX_SIZE = 256;
        val MAX_VALUE_DBL = 255.0;
        val  MAX_VALUE_INT = 255;
        val REVERSE = 1.0;

        val gammaR = IntArray(MAX_SIZE)
        val gammaG = IntArray(MAX_SIZE)
        val gammaB = IntArray(MAX_SIZE)

        for (i in 0 until MAX_SIZE) {
            gammaR[i] = Math.min(MAX_VALUE_INT,
                    (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / num) + 0.5).toInt())
            gammaG[i] = Math.min(MAX_VALUE_INT,
                    (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / num) + 0.5).toInt())
            gammaB[i] = Math.min(MAX_VALUE_INT,
                    (MAX_VALUE_DBL * Math.pow(i / MAX_VALUE_DBL, REVERSE / num) + 0.5).toInt())
        }

        var R: Int
        var G: Int
        var B: Int
        var pixel: Int

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                R = gammaR[Color.red(pixel)];
                G = gammaG[Color.green(pixel)];
                B = gammaB[Color.blue(pixel)];

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    private  fun Contraste(src: Bitmap, num: Int): Bitmap {
        val bmOut = Bitmap.createBitmap(src.width, src.height, src.config)

        var R: Int
        var G: Int
        var B: Int
        var pixel: Int
        val contrast = Math.pow((100.0 + num) / 100, 2.0)

        // scan through all pixels
        for (x in 0 until src.width) {
            for (y in 0 until src.height) {
                // get pixel color
                pixel = src.getPixel(x, y)
                R = Color.red(pixel);
                R = (((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0).toInt()
                if(R < 0) { R = 0; }
                else if(R > 255) { R = 255; }

                G = Color.green(pixel);
                G = (((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0).toInt()
                if(G < 0) { G = 0; }
                else if(G > 255) { G = 255; }

                B = Color.blue(pixel);
                B = (((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0).toInt()
                if(B < 0) { B = 0; }
                else if(B > 255) { B = 255; }

                bmOut.setPixel(x, y, Color.rgb(R, G, B))
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
                bmOut.setPixel(x, y, Color.rgb(R, G, B))
            }
        }
        src.recycle()
        return bmOut
    }

    private  fun GaussianBlur(src: Bitmap): Bitmap {
        val GaussianBlurConfig = Array(3, {DoubleArray(3)})
        GaussianBlurConfig[0][0]=1.0
        GaussianBlurConfig[0][1]=2.0
        GaussianBlurConfig[0][2]=1.0
        //
        GaussianBlurConfig[1][0]=2.0
        GaussianBlurConfig[1][1]=4.0
        GaussianBlurConfig[1][2]=2.0
        //
        GaussianBlurConfig[2][0]=1.0
        GaussianBlurConfig[2][1]=2.0
        GaussianBlurConfig[2][2]=1.0

        val convMatrix = MatrizConvolucion()
        convMatrix.ConvolutionMatrix(3)
        convMatrix.applyConfig(GaussianBlurConfig)
        convMatrix.Factor = 16
        convMatrix.Offset = 0
        return MatrizConvolucion().computeConvolution3x3(src, convMatrix)
    }
}