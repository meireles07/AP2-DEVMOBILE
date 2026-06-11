package com.seufilme.app

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

// FormularioActivity é a tela onde o usuário responde as perguntas
class FormularioActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_formulario)

        // Conectando os componentes do XML com o código Kotlin
        val etNome = findViewById<EditText>(R.id.etNome)
        val rgTipoConteudo = findViewById<RadioGroup>(R.id.rgTipoConteudo)
        val spinnerGenero = findViewById<Spinner>(R.id.spinnerGenero)
        val cbNetflix = findViewById<CheckBox>(R.id.cbNetflix)
        val cbPrime = findViewById<CheckBox>(R.id.cbPrime)
        val cbDisney = findViewById<CheckBox>(R.id.cbDisney)
        val switchAnimado = findViewById<Switch>(R.id.switchAnimado)
        val btnEnviar = findViewById<Button>(R.id.btnEnviar)

        val generos = listOf("Ação", "Comédia", "Drama", "Terror", "Romance", "Ficção Científica", "Animação", "Documentário")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, generos)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerGenero.adapter = adapter

        // Clique no botão de enviar
        btnEnviar.setOnClickListener {
            val nome = etNome.text.toString().trim()

            // Validação: verifica se o nome foi preenchido
            if (nome.isEmpty()) {
                etNome.error = "Por favor, digite seu nome"
                return@setOnClickListener
            }

            // Pega qual RadioButton foi selecionado
            val tipoConteudo = if (rgTipoConteudo.checkedRadioButtonId == R.id.rbFilme) "Filme" else "Série"

            // Pega o gênero selecionado no Spinner
            val generoFavorito = spinnerGenero.selectedItem.toString()

            // Monta a string de plataformas selecionadas
            val plataformas = mutableListOf<String>()
            if (cbNetflix.isChecked) plataformas.add("Netflix")
            if (cbPrime.isChecked) plataformas.add("Prime Video")
            if (cbDisney.isChecked) plataformas.add("Disney+")
            val plataforma = if (plataformas.isEmpty()) "Qualquer" else plataformas.joinToString(", ")

            // Pega o humor baseado no Switch
            val humor = if (switchAnimado.isChecked) "Animado" else "Relaxado"

            // Intent explícita: navega para ResultadosActivity passando os dados
            // "putExtra" envia dados de uma Activity para outra
            val intent = Intent(this, ResultadosActivity::class.java)
            intent.putExtra("nome", nome)
            intent.putExtra("genero_favorito", generoFavorito)
            intent.putExtra("plataforma", plataforma)
            intent.putExtra("humor", humor)
            intent.putExtra("tipo_conteudo", tipoConteudo)
            startActivity(intent)
        }
    }
}