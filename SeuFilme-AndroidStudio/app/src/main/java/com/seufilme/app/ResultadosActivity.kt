package com.seufilme.app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seufilme.app.adapter.Filme
import com.seufilme.app.adapter.FilmeAdapter
import com.seufilme.app.api.RetrofitClient
import com.seufilme.app.model.Resposta
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ResultadosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultados)

        val nome = intent.getStringExtra("nome") ?: ""
        val generoFavorito = intent.getStringExtra("genero_favorito") ?: ""
        val plataforma = intent.getStringExtra("plataforma") ?: ""
        val humor = intent.getStringExtra("humor") ?: ""
        val tipoConteudo = intent.getStringExtra("tipo_conteudo") ?: ""

        val tvOla = findViewById<TextView>(R.id.tvOla)
        val tvPerfil = findViewById<TextView>(R.id.tvPerfil)
        val recyclerFilmes = findViewById<RecyclerView>(R.id.recyclerFilmes)
        val btnSalvar = findViewById<Button>(R.id.btnSalvar)
        val btnHistorico = findViewById<Button>(R.id.btnHistorico)

        tvOla.text = "Olá, $nome!"
        tvPerfil.text = "🎬 $tipoConteudo • $generoFavorito • $plataforma"

        val filmes = gerarRecomendacoes(generoFavorito, tipoConteudo, plataforma, humor)

        recyclerFilmes.layoutManager = LinearLayoutManager(this)
        recyclerFilmes.adapter = FilmeAdapter(filmes)

        btnSalvar.setOnClickListener {
            val resposta = Resposta(
                nome = nome,
                genero_favorito = generoFavorito,
                plataforma = plataforma,
                humor = humor,
                tipo_conteudo = tipoConteudo
            )
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    RetrofitClient.apiService.criarResposta(resposta)
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ResultadosActivity, "Salvo com sucesso! ✅", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(this@ResultadosActivity, "Erro ao salvar: ${e.message}", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        btnHistorico.setOnClickListener {
            val intent = Intent(this, HistoricoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun gerarRecomendacoes(genero: String, tipo: String, plataforma: String, humor: String): List<Filme> {
        val todos = listOf(
            // Ação - Filme
            Filme("Extraction", "Ação", "Filme", "Netflix", "💥", "https://www.youtube.com/watch?v=L6P3nI6VnlY"),
            Filme("The Gray Man", "Ação", "Filme", "Netflix", "🕵️", "https://www.youtube.com/watch?v=ZCJpADiQkmQ"),
            Filme("Homem de Ferro", "Ação", "Filme", "Disney+", "🦸", "https://www.youtube.com/watch?v=2bVct5-ZxCQ"),
            Filme("John Wick", "Ação", "Filme", "Prime Video", "🔫", "https://www.youtube.com/watch?v=2AUmvWm5ZDQ"),
            // Ação - Série
            Filme("Demolidor", "Ação", "Série", "Netflix", "😈", "https://www.youtube.com/watch?v=9KZyUQpihsE"),
            // Comédia - Filme
            Filme("Murder Mystery", "Comédia", "Filme", "Netflix", "😂", "https://www.youtube.com/watch?v=Xe_GNGL1qzM"),
            Filme("A Família da Noiva", "Comédia", "Filme", "Prime Video", "🎊", "https://www.youtube.com/watch?v=OzICqCJaFiI"),
            Filme("Esqueceram de Mim", "Comédia", "Filme", "Disney+", "🏠", "https://www.youtube.com/watch?v=FBiNITpDDJA"),
            // Comédia - Série
            Filme("The Office", "Comédia", "Série", "Prime Video", "😄", "https://www.youtube.com/watch?v=tNcDHWpselE"),
            Filme("Friends", "Comédia", "Série", "Prime Video", "☕", "https://www.youtube.com/watch?v=hDNNmeeJs1Q"),
            // Drama - Série
            Filme("Breaking Bad", "Drama", "Série", "Netflix", "🧪", "https://www.youtube.com/watch?v=HhesaQXLuRY"),
            Filme("La Casa de Papel", "Drama", "Série", "Netflix", "🎭", "https://www.youtube.com/watch?v=iS5xXr-GOnM"),
            // Drama - Filme
            Filme("Forrest Gump", "Drama", "Filme", "Prime Video", "🏃", "https://www.youtube.com/watch?v=bLvqoHBptjg"),
            Filme("À Espera de um Milagre", "Drama", "Filme", "Netflix", "✨", "https://www.youtube.com/watch?v=IoqgBFaJSbU"),
            Filme("Whiplash", "Drama", "Filme", "Netflix", "🥁", "https://www.youtube.com/watch?v=qMbyMpEGVqo"),
            Filme("O Poderoso Chefão", "Drama", "Filme", "Prime Video", "🤵", "https://www.youtube.com/watch?v=sY1S34973zA"),
            // Ficção Científica - Filme
            Filme("Interestelar", "Ficção Científica", "Filme", "Prime Video", "🚀", "https://www.youtube.com/watch?v=i6avfCqKcQo"),
            // Ficção Científica - Série
            Filme("Stranger Things", "Ficção Científica", "Série", "Netflix", "👾", "https://www.youtube.com/watch?v=RMmGQNNl164"),
            // Terror - Filme
            Filme("Precisamos Falar Sobre o Kevin", "Terror", "Filme", "Netflix", "😱", "https://www.youtube.com/watch?v=37Hwj5j6z3Y"),
            // Terror - Série
            Filme("The Haunting of Hill House", "Terror", "Série", "Netflix", "👻", "https://www.youtube.com/watch?v=FUkAQ5BXBNI"),
            // Romance - Filme
            Filme("Diário de uma Paixão", "Romance", "Filme", "Netflix", "❤️", "https://www.youtube.com/watch?v=9FRllA0YP3Y"),
            Filme("A Barraca do Beijo", "Romance", "Filme", "Netflix", "💕", "https://www.youtube.com/watch?v=0gs3IbQk5aI"),
            // Romance - Série
            Filme("Bridgerton", "Romance", "Série", "Netflix", "💐", "https://www.youtube.com/watch?v=gpv7ayf_tyE"),
            Filme("Emily em Paris", "Romance", "Série", "Netflix", "🗼", "https://www.youtube.com/watch?v=GkEw_IE4tAs"),
            Filme("Virgin River", "Romance", "Série", "Netflix", "🌲", "https://www.youtube.com/watch?v=4wf6pSwSbRs"),
            // Documentário - Série
            Filme("Planeta Terra II", "Documentário", "Série", "Disney+", "🌍", "https://www.youtube.com/watch?v=aXHHw6YjOsM"),
            Filme("The Last Dance", "Documentário", "Série", "Netflix", "🏀", "https://www.youtube.com/watch?v=dynIcWHCFOI"),
            Filme("Making a Murderer", "Documentário", "Série", "Netflix", "🔍", "https://www.youtube.com/watch?v=IZdBnTmOKAI"),
            // Documentário - Filme
            Filme("Free Solo", "Documentário", "Filme", "Disney+", "🧗", "https://www.youtube.com/watch?v=urRVZ4SW7WU"),
            Filme("O Dilema das Redes", "Documentário", "Filme", "Netflix", "📱", "https://www.youtube.com/watch?v=uaaC57tcci0"),
            // Animação - Filme
            Filme("Divertidamente", "Animação", "Filme", "Disney+", "🎭", "https://www.youtube.com/watch?v=LSpeM7G4zfY"),
            Filme("Klaus", "Animação", "Filme", "Netflix", "🎅", "https://www.youtube.com/watch?v=opsRFVTMr5A"),
            // Animação - Série
            Filme("Arcane", "Animação", "Série", "Netflix", "⚔️", "https://www.youtube.com/watch?v=4Ps6nV4wiCE"),
            Filme("Avatar: A Lenda de Aang", "Animação", "Série", "Netflix", "🌊", "https://www.youtube.com/watch?v=d1Gu9gBdSiA"),
            Filme("Família do Futuro", "Animação", "Série", "Disney+", "🤖", "https://www.youtube.com/watch?v=6H0YFnCEalE")
        )

        // Separa as plataformas selecionadas (ex: "Netflix, Prime Video")
        val plataformasSelecionadas = plataforma.split(", ").map { it.trim() }

        // Filtra por gênero E tipo E plataforma
        val filtrados = todos.filter { filme ->
            val generoOk = filme.genero.equals(genero, ignoreCase = true)
            val tipoOk = filme.tipo.equals(tipo, ignoreCase = true)
            val plataformaOk = plataforma == "Qualquer" || plataformasSelecionadas.any {
                filme.plataforma.contains(it, ignoreCase = true)
            }
            generoOk && tipoOk && plataformaOk
        }

        // Se não achar nada com todos os filtros, filtra só por tipo
        val resultado = if (filtrados.isEmpty()) {
            todos.filter { it.tipo.equals(tipo, ignoreCase = true) }
        } else {
            filtrados
        }

        return if (resultado.isEmpty()) todos else resultado
    }
}