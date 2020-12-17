package com.example.examenfinal

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.view.KeyEvent
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.isVisible
import java.lang.Integer.parseInt


class MainActivity2 : AppCompatActivity() {

    lateinit var fotografia: ImageView
    lateinit var Uri: Uri
    lateinit var deshacer: View
    lateinit var bitmap: Bitmap
    lateinit var cambio : Bitmap
    lateinit var spinner1 : Spinner
    lateinit var spinner2: Spinner
    lateinit var spinner3: Spinner
    lateinit var brillo : TextView
    lateinit var gamma : TextView
    lateinit var saturacion : TextView
    lateinit var cdepth : TextView
    //lateinit var tipodefiltro: TextView
    lateinit var Contraste : TextView
    lateinit var filtro: RadioGroup
    lateinit var contentCdepth: LinearLayout
    lateinit var contentSaturacion : LinearLayout
    lateinit var contentBrillo : LinearLayout
    lateinit var contentContraste : LinearLayout
    lateinit var contentGamma : LinearLayout
    lateinit var contentFiltro : LinearLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        //filtro.clearCheck()
        val efecto = BitmapProcessing()
        val image = intent.getStringExtra("image")
        Toast.makeText(applicationContext, " " + image, Toast.LENGTH_SHORT).show()
        deshacer = findViewById(R.id.fab)
        fotografia = findViewById(R.id.fotografia)
        spinner1 = findViewById(R.id.idBasicos)
        spinner2 = findViewById(R.id.idConvolucion)
        spinner3 = findViewById(R.id.idOtros)
        cdepth = findViewById(R.id.Cdepth)
        saturacion = findViewById(R.id.Saturacion)
        brillo = findViewById(R.id.Brillo)
        Contraste = findViewById(R.id.Contraste)
        filtro = findViewById(R.id.Filtro)
        gamma = findViewById(R.id.Gamma)
        //tipodefiltro = findViewById(R.id.txtElige)
        contentCdepth = findViewById(R.id.PanelCdepth)
        contentSaturacion = findViewById(R.id.PanelSaturacion)
        contentBrillo = findViewById(R.id.PanelBrillo)
        contentFiltro = findViewById(R.id.PanelFiltro)
        contentContraste = findViewById(R.id.PanelContraste)
        contentGamma = findViewById(R.id.PanelGamma)
        val filtrosB = resources.getStringArray(R.array.Fbasicos)
        val filtrosC = resources.getStringArray(R.array.Fconvolucion)
        val filtrosO = resources.getStringArray(R.array.Fotros)
       // fotografia2 = findViewById(R.id.fotografia2)
        if (image != null) {
            Uri = image.toUri()
            fotografia.setImageURI(image.toUri())
            //bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            //fotografia.setImageBitmap(bitmap)
        }



        var scaleFactor = 1f
        val scaleGestureDetector = ScaleGestureDetector(
                this,
                object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                    override fun onScale(detector: ScaleGestureDetector): Boolean {
                        scaleFactor *= detector.scaleFactor
                        scaleFactor = scaleFactor.coerceIn(0.1f, 5.0f)

                        fotografia.scaleX = scaleFactor
                        fotografia.scaleY = scaleFactor

                        return super.onScale(detector)
                    }
                }
        )
        deshacer.setOnClickListener {
            fotografia.setImageURI(Uri)
        }

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    var filtroSeleccionado = filtrosB[position]
                    var cambio : Bitmap?
                    contentCdepth.isVisible = false;
                    contentSaturacion.isVisible = false;
                    contentFiltro.isVisible = false;
                    contentGamma.isVisible = false;
                    contentBrillo.isVisible = false;
                    contentContraste.isVisible = false;
                    if (filtroSeleccionado != "Filtros Basicos") {
                        bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                        when(filtroSeleccionado) {
                            "Negativo" -> cambio = efecto.Negativo(bitmap)
                            "Escala de Grises" -> cambio = efecto.Grises(bitmap)
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

        fotografia.setOnTouchListener { _, event ->
            scaleGestureDetector.onTouchEvent(event)
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var filtroSeleccionado2 = filtrosC[position]
                var cambio2 : Bitmap?
                contentCdepth.isVisible = false;
                contentSaturacion.isVisible = false;
                contentFiltro.isVisible = false;
                contentGamma.isVisible = false;
                contentBrillo.isVisible = false;
                contentContraste.isVisible = false;

                if (filtroSeleccionado2 != "Filtros de Convolucion") {
                    bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                    when(filtroSeleccionado2) {
                        "Smoothing" -> cambio2 = efecto.smoothing(bitmap)
                        "Gaussian Blur" -> cambio2 = efecto.gaussianBlur(bitmap)
                        "Sharpen" -> cambio2 = efecto.sharpen(bitmap)
                        "Mean Removal" -> cambio2 = efecto.meanRemoval(bitmap)
                        "Embossing" -> cambio2 = efecto.embossing(bitmap)
                        "Edge Detection" -> cambio2 = efecto.edgeDetection(bitmap)
                        else-> {
                            cambio2 = bitmap
                        }
                    }
                    //contentBrillo.isVisible = false;
                    fotografia.setImageBitmap(cambio2)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var filtroSeleccionado3 = filtrosO[position]
                var cambio2 : Bitmap?
                contentCdepth.isVisible = false;
                contentSaturacion.isVisible = false;
                contentFiltro.isVisible = false;
                contentGamma.isVisible = false;
                contentBrillo.isVisible = false;
                contentContraste.isVisible = false;
                if (filtroSeleccionado3 != "Otros") {
                    bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                    when(filtroSeleccionado3) {
                        "Sepia" -> cambio2 = efecto.Sepia(bitmap)
                        "Sketch" -> cambio2 = efecto.sketch(bitmap)
                        "Noise" -> cambio2 = efecto.Noise(bitmap)
                        "Saturation" -> {
                            contentSaturacion.isVisible = true
                            cambio2 = bitmap
                        }
                        "Cdepth" -> {
                            contentCdepth.isVisible = true
                            cambio2 = bitmap
                        }

                        else-> {
                            cambio2 = bitmap
                        }
                    }
                    //contentBrillo.isVisible = false;
                    fotografia.setImageBitmap(cambio2)
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        brillo.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            //fotografia.setImageURI(image?.toUri())
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio : Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (brillo.text != null) {
                    if(parseInt(brillo.text.toString()) >= -255 &&  parseInt(brillo.text.toString()) <= 255){
                        cambio = efecto.Brillo(bitmap, parseInt(brillo.text.toString()))
                    }else
                    {
                        cambio = efecto.Brillo(bitmap, 0)
                    }

                } else {
                    //Default
                    cambio = efecto.Brillo(bitmap, 50)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        cdepth.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            //fotografia.setImageURI(image?.toUri())
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio : Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (cdepth.text != null) {
                    cambio = efecto.Cdepth(bitmap, parseInt(cdepth.text.toString()))
                } else {
                    //Default
                    cambio = efecto.Cdepth(bitmap, 32)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        saturacion.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            //fotografia.setImageURI(image?.toUri())
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio : Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (saturacion.text != null) {
                    if(parseInt(saturacion.text.toString()) >= 0 &&  parseInt(saturacion.text.toString()) <= 200){
                        cambio = efecto.Saturation(bitmap, parseInt(saturacion.text.toString()))
                    }else
                    {
                        cambio = efecto.Saturation(bitmap, 100)
                    }
                } else {
                    //Default
                    cambio = efecto.Saturation(bitmap, 50)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        Contraste.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            //fotografia.setImageURI(image?.toUri())
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio: Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (Contraste.text != null) {
                    if(parseInt(Contraste.text.toString()) >= -100 &&  parseInt(Contraste.text.toString()) <= 100){
                        cambio = efecto.Contraste(bitmap, parseInt(Contraste.text.toString()))
                    }else
                    {
                        cambio = efecto.Contraste(bitmap, 0)
                    }
                } else {
                    //Default
                    cambio = efecto.Contraste(bitmap, 50)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        gamma.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            //fotografia.setImageURI(image?.toUri())
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio : Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (gamma.text != null) {
                    if(parseInt(gamma.text.toString()) in 0..48){
                        cambio = efecto.Gamma(bitmap, (gamma.text.toString().toDouble()))
                    }else
                    {
                        cambio = efecto.Gamma(bitmap, 3.0)
                    }
                } else {
                    //Default
                    cambio = efecto.Gamma(bitmap, 3.0)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        filtro.setOnCheckedChangeListener(RadioGroup.OnCheckedChangeListener { radioGroup, i ->
            //fotografia.setImageURI(image?.toUri())
            var cambio : Bitmap
            bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            if (i == R.id.Rojo) {
                cambio = efecto.Filtro(bitmap, 1)
            } else if (i == R.id.Verde) {
                cambio = efecto.Filtro(bitmap, 2)
            } else {
                cambio = efecto.Filtro(bitmap, 3)
            }
            fotografia.setImageBitmap(cambio)
        })
    }

}