package com.seufilme.app.model

// Essa classe representa os dados que o app envia e recebe da API
// "data class" em Kotlin é uma classe feita para armazenar dados
data class Resposta(
    val id: Int = 0,                    // ID do usuário única
    val nome: String = "",              // Nome do usuário
    val genero_favorito: String = "",   // Gênero favorito (Ação, Comédia, etc.)
    val plataforma: String = "",        // Plataforma (Netflix, Prime, etc.)
    val humor: String = "",             // Humor atual do usuário
    val tipo_conteudo: String = "",     // Filme ou Série
    val data_criacao: String = ""       // Data que foi salvo
)