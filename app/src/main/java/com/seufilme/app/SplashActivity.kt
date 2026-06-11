package com.seufilme.app

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

// SplashActivity é a primeira tela que o usuário vê ao abrir o app
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Define qual layout XML essa Activity vai usar
        setContentView(R.layout.activity_splash)

        // Handler é usado para executar algo após um delay (atraso)
        // Aqui estamos esperando 2 segundos antes de ir para a próxima tela
        Handler(Looper.getMainLooper()).postDelayed({

            // Isso é uma navegação entre telas — Intent EXPLÍCITA
            val intent = Intent(this, FormularioActivity::class.java)
            startActivity(intent)

            finish()

        }, 2000) // 2000 milissegundos = 2 segundos
    }
}