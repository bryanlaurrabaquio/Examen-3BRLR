package com.example.examen3

import android.content.Intent
import android.widget.Button
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val btnIniciarSesion = findViewById<Button>(R.id.btnIniciarSesion)

        btnIniciarSesion.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            intent.putExtra("NOMBRE_USUARIO", "Juan")
            startActivity(intent)
        }
    }
}