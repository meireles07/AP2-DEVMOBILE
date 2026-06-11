package com.seufilme.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.seufilme.app.R

// HistoricoActivity é a tela que mostra o histórico de buscas
// Ela usa um Fragment para exibir a lista — isso é obrigatório na prova!
class HistoricoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_historico)

        // Carrega o HistoricoFragment dentro do fragmentContainer
        // supportFragmentManager gerencia os Fragments da Activity
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, HistoricoFragment())
                .commit()
        }
    }
}