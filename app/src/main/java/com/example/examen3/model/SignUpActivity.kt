package com.example.tuapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen3.R

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        val etUsuario = findViewById<EditText>(R.id.etNuevoUsuario)
        val etContrasena = findViewById<EditText>(R.id.etNuevaContrasena)
        val btnRegistrarse = findViewById<Button>(R.id.btnRegistrarse)

        btnRegistrarse.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val contrasena = etContrasena.text.toString()

            if (usuario.isNotEmpty() && contrasena.isNotEmpty()) {
                Toast.makeText(this, "Usuario $usuario registrado correctamente", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Por favor ingresa todos los datos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
