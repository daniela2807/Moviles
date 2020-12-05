package com.example.examenfinal

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
    lateinit var cambio : Bitmap
    lateinit var spinner1 : Spinner
    lateinit var spinner2: Spinner
    lateinit var spinner3: Spinner
    lateinit var brillo : TextView
    lateinit var gamma : TextView
    //lateinit var tipodefiltro: TextView
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
        val efecto = BitmapProcessing()
        val image = intent.getStringExtra("image")
        Toast.makeText(applicationContext, " " + image, Toast.LENGTH_SHORT).show()
        fotografia = findViewById(R.id.fotografia)
        spinner1 = findViewById(R.id.idBasicos)
        spinner2 = findViewById(R.id.idConvolucion)
        spinner3 = findViewById(R.id.idOtros)
        brillo = findViewById(R.id.Brillo)
        Contraste = findViewById(R.id.Contraste)
        filtro = findViewById(R.id.Filtro)
        gamma = findViewById(R.id.Gamma)
        //tipodefiltro = findViewById(R.id.txtElige)
        contentBrillo = findViewById(R.id.PanelBrillo)
        contentFiltro = findViewById(R.id.PanelFiltro)
        contentContraste = findViewById(R.id.PanelContraste)
        contentGamma = findViewById(R.id.PanelGamma)
        val filtrosB = resources.getStringArray(R.array.Fbasicos)
        val filtrosC = resources.getStringArray(R.array.Fconvolucion)
        val filtrosO = resources.getStringArray(R.array.Fotros)
       // fotografia2 = findViewById(R.id.fotografia2)
        if (image != null) {
            fotografia.setImageURI(image.toUri())
            //bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
            //fotografia.setImageBitmap(bitmap)
        }

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    var filtroSeleccionado = filtrosB[position]
                    var cambio : Bitmap?
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

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                var filtroSeleccionado2 = filtrosC[position]
                var cambio2 : Bitmap?
                contentFiltro.isVisible = false;
                contentGamma.isVisible = false;
                contentBrillo.isVisible = false;
                contentContraste.isVisible = false;
                if (filtroSeleccionado2 != "Filtros de Convolucion") {
                    bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                    when(filtroSeleccionado2) {
                        "Gaussian Blur" -> cambio2 = efecto.gaussianBlur(bitmap)
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
                contentFiltro.isVisible = false;
                contentGamma.isVisible = false;
                contentBrillo.isVisible = false;
                contentContraste.isVisible = false;
                if (filtroSeleccionado3 != "Otros") {
                    bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                    when(filtroSeleccionado3) {
                        "Sepia" -> cambio2 = efecto.Sepia(bitmap)
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
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio : Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (brillo.text != null) {
                    cambio = efecto.Brillo(bitmap, parseInt(brillo.text.toString()))
                } else {
                    //Default
                    cambio = efecto.Brillo(bitmap, 50)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        Contraste.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio: Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (Contraste.text != null) {
                    cambio = efecto.Contraste(bitmap, parseInt(Contraste.text.toString()))
                } else {
                    //Default
                    cambio = efecto.Contraste(bitmap, 50)
                }
                fotografia.setImageBitmap(cambio)
            }
            false
        })

        gamma.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var cambio : Bitmap?
                //fotografia.setImageURI(image?.toUri())
                bitmap = (fotografia.getDrawable() as BitmapDrawable).bitmap
                if (gamma.text != null) {
                    cambio = efecto.Gamma(bitmap, (gamma.text.toString()).toDouble())
                } else {
                    //Default
                    cambio = efecto.Gamma(bitmap, 5.0)
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