package com.seufilme.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.seufilme.app.R
import com.seufilme.app.model.Resposta

// HistoricoAdapter conecta a lista de respostas salvas com o RecyclerView do histórico
class HistoricoAdapter(
    private val respostas: MutableList<Resposta>,
    private val onDeletar: (Resposta) -> Unit
) : RecyclerView.Adapter<HistoricoAdapter.HistoricoViewHolder>() {

    inner class HistoricoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNome: TextView = itemView.findViewById(R.id.tvNomeHistorico)
        val tvDetalhes: TextView = itemView.findViewById(R.id.tvDetalhesHistorico)
        val tvData: TextView = itemView.findViewById(R.id.tvDataHistorico)
        val btnDeletar: Button = itemView.findViewById(R.id.btnDeletar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_historico, parent, false)
        return HistoricoViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoricoViewHolder, position: Int) {
        val resposta = respostas[position]

        holder.tvNome.text = resposta.nome
        holder.tvDetalhes.text = "${resposta.genero_favorito} • ${resposta.tipo_conteudo} • ${resposta.plataforma}"
        holder.tvData.text = resposta.data_criacao.take(10) // pega só a data sem a hora

        // Quando clica em deletar, chama a função passada pelo Fragment
        holder.btnDeletar.setOnClickListener {
            onDeletar(resposta)
        }
    }

    override fun getItemCount(): Int = respostas.size

    // Remove um item da lista e notifica o RecyclerView para atualizar a tela
    fun removerItem(resposta: Resposta) {
        val index = respostas.indexOf(resposta)
        if (index != -1) {
            respostas.removeAt(index)
            notifyItemRemoved(index)
        }
    }
}