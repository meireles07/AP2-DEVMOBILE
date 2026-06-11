package com.seufilme.app

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

// DetalheActivity mostra os detalhes de um filme específico
// Ela recebe os dados do filme via Intent enviada pelo FilmeAdapter
class DetalheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalhe)

        // Recebe os dados do filme enviados pelo adapter
        val titulo = intent.getStringExtra("titulo") ?: ""
        val genero = intent.getStringExtra("genero") ?: ""
        val tipo = intent.getStringExtra("tipo") ?: ""
        val plataforma = intent.getStringExtra("plataforma") ?: ""
        val emoji = intent.getStringExtra("emoji") ?: "🎬"
        val trailerUrl = intent.getStringExtra("trailerUrl") ?: ""

        // Conecta os componentes do XML
        val tvEmoji = findViewById<TextView>(R.id.tvEmojiDetalhe)
        val tvTitulo = findViewById<TextView>(R.id.tvTituloDetalhe)
        val tvGenero = findViewById<TextView>(R.id.tvGeneroDetalhe)
        val tvPlataforma = findViewById<TextView>(R.id.tvPlataformaDetalhe)
        val btnTrailer = findViewById<Button>(R.id.btnAssistirTrailer)
        val btnCompartilhar = findViewById<Button>(R.id.btnCompartilhar)
        val btnVoltar = findViewById<Button>(R.id.btnVoltar)

        // Preenche os dados na tela
        tvEmoji.text = emoji
        tvTitulo.text = titulo
        tvGenero.text = "$genero • $tipo"
        tvPlataforma.text = "Disponível na $plataforma"

        // Intent IMPLÍCITA: abre o trailer no YouTube/navegador
        btnTrailer.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(trailerUrl))
            startActivity(intent)
        }

        // Intent IMPLÍCITA: compartilha o filme via qualquer app (WhatsApp, etc.)
        btnCompartilhar.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)
            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, "Assista $titulo na $plataforma! $trailerUrl")
            startActivity(Intent.createChooser(intent, "Compartilhar via"))
        }

        // Volta para a tela anterior
        btnVoltar.setOnClickListener {
            finish()
        }
    }
}