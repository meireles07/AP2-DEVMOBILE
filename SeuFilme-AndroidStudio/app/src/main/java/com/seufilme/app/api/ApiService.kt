package com.seufilme.app.api

import com.seufilme.app.model.Resposta
import retrofit2.http.*

// Essa interface define os endpoints que o app pode chamar na API
// Cada função aqui corresponde a uma rota da nossa API FastAPI
interface ApiService {

    // POST /respostas — envia o formulário para a API salvar no banco
    @POST("respostas")
    suspend fun criarResposta(@Body resposta: Resposta): Resposta

    // GET /historico — busca todas as respostas salvas
    @GET("historico")
    suspend fun listarHistorico(): List<Resposta>

    // DELETE /historico/{id} — deleta uma resposta pelo ID
    @DELETE("historico/{id}")
    suspend fun deletarResposta(@Path("id") id: Int): Map<String, String>
}