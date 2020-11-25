package com.example.examenfinal

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.*


class MenuClass : LinearLayout {

    private lateinit var filtroSeleccionado: String
    private lateinit var txtFiltro: TextView

    constructor(ctx: Context) : super(ctx) {
        inicializar()
    }
    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        inicializar()
    }
    constructor(ctx: Context, attrs: AttributeSet, defStyleAttr: Int) : super(ctx, attrs, defStyleAttr) {
        inicializar()
    }

    fun inicializar(){
        val li = LayoutInflater.from(context)
        li.inflate(R.layout.menu_eleccion, this, true)
        txtFiltro = findViewById(R.id.txtElige)

        //Spinner para la lista de filtros básicos
        val filtrosB = resources.getStringArray(R.array.Fbasicos)
        val spinner1 = findViewById<Spinner>(R.id.idBasicos)
        val adapter1 = ArrayAdapter(context, android.R.layout.simple_spinner_item, filtrosB)
        spinner1.adapter = adapter1
        if (spinner1 != null){
            spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    filtroSeleccionado = filtrosB[position]
                    if (filtroSeleccionado != "Filtros Basicos") {
                        txtFiltro.text = filtroSeleccionado
                        val text = "Seleccionaste: "+ filtroSeleccionado
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(context, text, duration)
                        toast.show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }

        //Spinner para la lista de filtros de convolucion
        val filtrosC = resources.getStringArray(R.array.Fconvolucion)
        val spinner2 = findViewById<Spinner>(R.id.idConvolucion)
        val adapter2 = ArrayAdapter(context, android.R.layout.simple_spinner_item, filtrosC)
        spinner2.adapter = adapter2
        if (spinner2 != null){
            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    filtroSeleccionado = filtrosC[position]
                    if (filtroSeleccionado != "Filtros de Convolucion") {
                        txtFiltro.text = filtroSeleccionado
                        val text = "Seleccionaste: "+ filtroSeleccionado
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(context, text, duration)
                        toast.show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }

        //Spinner para lista de Otros filtros
        val filtrosO = resources.getStringArray(R.array.Fotros)
        val spinner3 = findViewById<Spinner>(R.id.idOtros)
        val adapter3 = ArrayAdapter(context, android.R.layout.simple_spinner_item, filtrosO)
        spinner3.adapter = adapter3
        if (spinner3 != null){
            spinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    filtroSeleccionado = filtrosO[position]
                    if (filtroSeleccionado != "Otros") {
                        txtFiltro.text = filtroSeleccionado
                        val text = "Seleccionaste: "+ filtroSeleccionado
                        val duration = Toast.LENGTH_SHORT
                        val toast = Toast.makeText(context, text, duration)
                        toast.show()
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            }
        }
    }




}