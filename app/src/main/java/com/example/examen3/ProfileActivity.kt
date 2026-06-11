package com.example.examen3

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examen3.model.DBHelper

class ProfileActivity : AppCompatActivity() {

    private lateinit var tvNombreJugador: TextView
    private lateinit var tvUltimaConexion: TextView
    private lateinit var btnCerrarSesion: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        tvNombreJugador = findViewById(R.id.tvNombreJugador)
        tvUltimaConexion = findViewById(R.id.tvUltimaConexion)
        btnCerrarSesion = findViewById(R.id.btnCerrarSesion)

        val nombreUsuario = intent.getStringExtra("NOMBRE_USUARIO") ?: "Jugador"
        tvNombreJugador.text = nombreUsuario

        val dbHelper = DBHelper(this)
        val ultimaConexionGuardada = dbHelper.obtenerUltimaConexion(nombreUsuario)

        val fechaActual = obtenerFechaActual()
        dbHelper.actualizarUltimaConexion(nombreUsuario, fechaActual)

        tvUltimaConexion.text = if (ultimaConexionGuardada != null) {
            "Última conexión: $ultimaConexionGuardada"
        } else {
            "Primera conexión"
        }

        btnCerrarSesion.setOnClickListener {
            Toast.makeText(this, "Hasta pronto $nombreUsuario", Toast.LENGTH_SHORT).show()
            finish()
        }
    }


    private fun obtenerFechaActual(): String {
        return java.text.SimpleDateFormat(
            "dd/MM/yyyy HH:mm",
            java.util.Locale.getDefault()
        ).format(java.util.Date())
    }
}