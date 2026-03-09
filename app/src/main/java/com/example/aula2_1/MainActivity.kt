package com.example.aula2_1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private val TAG = "LifecycleDemo"
    private lateinit var tvLog: TextView
    private var logText = ""

    private lateinit var tvContador: TextView
    private lateinit var btnIncrementar: Button
    private lateinit var btnDecrementar: Button
    private lateinit var btnReset: Button

    private var contador = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar views
        tvContador = findViewById(R.id.tvContador)
        btnIncrementar = findViewById(R.id.btnIncrementar)
        btnDecrementar = findViewById(R.id.btnDecrementar)
        btnReset = findViewById(R.id.btnReset)

        // Restaurar estado
        contador = savedInstanceState?.getInt("contador") ?: 0
        atualizarUI()

        // Listeners
        btnIncrementar.setOnClickListener {
            contador++
            atualizarUI()
        }

        btnDecrementar.setOnClickListener {
            contador--
            atualizarUI()
        }

        btnReset.setOnClickListener {
            contador = 0
            atualizarUI()
        }
    }

    private fun atualizarUI() {
        tvContador.text = contador.toString()
        tvContador.setTextColor(
            when {
                contador > 0 -> Color.GREEN
                contador < 0 -> Color.RED
                else -> Color.GRAY
            }
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt("contador", contador)
    }

    override fun onStart() {
        super.onStart()
        addLog("onStart")
    }

    override fun onResume() {
        super.onResume()
        addLog("onResume")
    }

    override fun onPause() {
        super.onPause()
        addLog("onPause")
    }

    override fun onStop() {
        super.onStop()
        addLog("onStop")
    }

    override fun onRestart() {
        super.onRestart()
        addLog("onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    private fun addLog(method: String) {
        logText += "$method → "
        tvLog.text = logText
        Log.d(TAG, method)
    }
}