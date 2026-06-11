package com.seufilme.app.adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seufilme.app.R
import com.seufilme.app.DetalheActivity

// Essa classe representa um filme na lista
data class Filme(
    val titulo: String,
    val genero: String,
    val tipo: String,
    val plataforma: String,
    val emoji: String,
    val trailerUrl: String
)

// FilmeAdapter conecta a lista de filmes com o RecyclerView
class FilmeAdapter(private val filmes: List<Filme>) :
    RecyclerView.Adapter<FilmeAdapter.FilmeViewHolder>() {

    // ViewHolder guarda as referências dos componentes de cada card
    inner class FilmeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvEmoji: TextView = itemView.findViewById(R.id.tvEmoji)
        val tvTitulo: TextView = itemView.findViewById(R.id.tvTituloFilme)
        val tvGeneroTipo: TextView = itemView.findViewById(R.id.tvGeneroTipo)
        val tvPlataforma: TextView = itemView.findViewById(R.id.tvPlataforma)
        val btnDetalhes: Button = itemView.findViewById(R.id.btnDetalhes)
    }

    // Cria o ViewHolder inflando o layout item_filme.xml
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filme, parent, false)
        return FilmeViewHolder(view)
    }

    // Preenche cada card com os dados do filme correspondente
    override fun onBindViewHolder(holder: FilmeViewHolder, position: Int) {
        val filme = filmes[position]
        holder.tvEmoji.text = filme.emoji
        holder.tvTitulo.text = filme.titulo
        holder.tvGeneroTipo.text = "${filme.genero} • ${filme.tipo}"
        holder.tvPlataforma.text = filme.plataforma

        // Intent IMPLÍCITA: abre o trailer no navegador/YouTube
        // Não dizemos qual app abrir — o Android escolhe automaticamente
        holder.btnDetalhes.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetalheActivity::class.java)
            intent.putExtra("titulo", filme.titulo)
            intent.putExtra("genero", filme.genero)
            intent.putExtra("tipo", filme.tipo)
            intent.putExtra("plataforma", filme.plataforma)
            intent.putExtra("emoji", filme.emoji)
            intent.putExtra("trailerUrl", filme.trailerUrl)
            holder.itemView.context.startActivity(intent)
        }
    }

    // Retorna quantos filmes tem na lista
    override fun getItemCount(): Int = filmes.size
}