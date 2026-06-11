package com.seufilme.app

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.seufilme.app.adapter.HistoricoAdapter
import com.seufilme.app.api.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

// HistoricoFragment é um Fragment — um pedaço de tela dentro da HistoricoActivity
// Ele busca o histórico da API e exibe em uma lista
class HistoricoFragment : Fragment() {

    private lateinit var recyclerHistorico: RecyclerView
    private lateinit var tvVazio: TextView

    // onCreateView é chamado quando o Fragment precisa criar sua interface
    // É como o setContentView mas para Fragments
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_historico, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerHistorico = view.findViewById(R.id.recyclerHistorico)
        tvVazio = view.findViewById(R.id.tvVazio)

        recyclerHistorico.layoutManager = LinearLayoutManager(requireContext())

        // Carrega o histórico da API
        carregarHistorico()
    }

    private fun carregarHistorico() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val respostas = RetrofitClient.apiService.listarHistorico().toMutableList()
                withContext(Dispatchers.Main) {
                    if (respostas.isEmpty()) {
                        // Mostra mensagem de vazio e esconde o RecyclerView
                        tvVazio.visibility = View.VISIBLE
                        recyclerHistorico.visibility = View.GONE
                    } else {
                        tvVazio.visibility = View.GONE
                        recyclerHistorico.visibility = View.VISIBLE

                        // Cria o adapter passando a lista e a função de deletar
                        val adapter = HistoricoAdapter(respostas) { resposta ->
                            deletarResposta(resposta.id)
                        }
                        recyclerHistorico.adapter = adapter
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Erro ao carregar histórico", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun deletarResposta(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                RetrofitClient.apiService.deletarResposta(id)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Removido com sucesso!", Toast.LENGTH_SHORT).show()
                    carregarHistorico()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Erro ao remover", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}