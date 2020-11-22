package com.example.examenfinal

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.widget.ActionMenuView
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var btnGaleria: Button
    private lateinit var Imagen: ImageView
    private val REQUEST_GALERY = 1001 // cualquier valor diferente a -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnGaleria = findViewById(R.id.btnGaleria)
        Imagen = findViewById(R.id.imagen)
        abreGaleria()
    }

    private fun abreGaleria(){
        btnGaleria.setOnClickListener(){
            //Verificamos que version de android instalada en el telefono
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                //Pregintamos si tiene permiso
                if(checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
                    //Pedimos permiso
                    val permisoArchivos = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permisoArchivos, REQUEST_GALERY)
                }else{
                    //Entonces tiene permiso
                    muestraGaleria()
                }
            }else{
                //Tiene version de Lolipop hacia abajo y tiene permiso
                muestraGaleria()
            }
        }
    }

    //Chceamos si el usuario dio permiso a la app
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_GALERY ->{
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    muestraGaleria()
                else
                    Toast.makeText(applicationContext,"No podemos acceder a tus imagenes",Toast.LENGTH_SHORT).show()
            }
        }
    }

    //Abre la ventana donde se muestra la galeria de fotos
    private fun muestraGaleria(){
        //TODO muestra galeria de imagenes
        val intentGaleria = Intent(Intent.ACTION_PICK)
        intentGaleria.type="image/*"
        startActivityForResult(intentGaleria, REQUEST_GALERY)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK && requestCode == REQUEST_GALERY){
            Imagen.setImageURI(data?.data)
        }
    }
}