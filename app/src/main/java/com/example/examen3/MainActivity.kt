package com.example.examen3

import android.content.Intent
import android.widget.Button
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.examen3.model.DBHelper

class MainActivity : AppCompatActivity() {

    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        dbHelper = DBHelper(this)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etContrasena = findViewById<EditText>(R.id.etContrasena)
        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener {
            val nombre = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            if (dbHelper.validarCredenciales(nombre, contrasena)) {
                val intent = Intent(this, ProfileActivity::class.java)
                intent.putExtra("NOMBRE_USUARIO", nombre)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
