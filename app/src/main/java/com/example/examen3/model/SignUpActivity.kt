package com.example.examen3.model

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen3.R

class SignUpActivity : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        dbHelper = DBHelper(this)

        val etUsuario = findViewById<EditText>(R.id.etNuevoUsuario)
        val etContrasena = findViewById<EditText>(R.id.etNuevaContrasena)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        btnRegistrarse.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                val exito = dbHelper.insertarJugador(usuario, contrasena)
                if (exito) {
                    Toast.makeText(this, "Usuario $usuario registrado en BD", Toast.LENGTH_LONG).show()
                    etUsuario.text.clear()
                    etContrasena.text.clear()
                } else {
                    Toast.makeText(this, "Error al registrar usuario", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
