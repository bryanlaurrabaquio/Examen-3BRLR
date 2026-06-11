package com.example.examen3.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "jugadores.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_JUGADOR = "Jugador"
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE = "nombre"
        const val COLUMN_CONTRASENA = "contrasena"
        const val COLUMN_ULTIMA_CONEXION = "ultimaConexion"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_JUGADOR (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT NOT NULL,
                $COLUMN_CONTRASENA TEXT NOT NULL,
                $COLUMN_ULTIMA_CONEXION TEXT
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_JUGADOR")
        onCreate(db)
    }

    fun insertarJugador(nombre: String, contrasena: String): Boolean {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE, nombre)
            put(COLUMN_CONTRASENA, contrasena)
        }
        val result = db.insert(TABLE_JUGADOR, null, values)
        db.close()
        return result != -1L
    }

    fun validarCredenciales(nombre: String, contrasena: String): Boolean {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_JUGADOR WHERE $COLUMN_NOMBRE=? AND $COLUMN_CONTRASENA=?"
        val cursor = db.rawQuery(query, arrayOf(nombre, contrasena))
        val existe = cursor.count > 0
        cursor.close()
        db.close()
        return existe
    }

    fun actualizarUltimaConexion(nombre: String, fecha: String) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_ULTIMA_CONEXION, fecha)
        }
        db.update(TABLE_JUGADOR, values, "$COLUMN_NOMBRE=?", arrayOf(nombre))
        db.close()
    }

    fun obtenerUltimaConexion(nombre: String): String? {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_JUGADOR,
            arrayOf(COLUMN_ULTIMA_CONEXION),
            "$COLUMN_NOMBRE=?",
            arrayOf(nombre),
            null, null, null
        )

        var ultimaConexion: String? = null
        if (cursor.moveToFirst()) {
            ultimaConexion = cursor.getString(0)
        }
        cursor.close()
        db.close()
        return ultimaConexion
    }

    fun existeJugador(nombre: String): Boolean {
        val db = readableDatabase
        val cursor = db.query(
            TABLE_JUGADOR,
            arrayOf(COLUMN_ID),
            "$COLUMN_NOMBRE=?",
            arrayOf(nombre),
            null, null, null
        )
        return TODO("Provide the return value")
    }
}