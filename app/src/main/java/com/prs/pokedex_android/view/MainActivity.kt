package com.prs.pokedex_android.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prs.pokedex_android.R
import com.prs.pokedex_android.domain.Pokemon
import com.prs.pokedex_android.domain.PokemonType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val Charmander = Pokemon(
            "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/004.png",
            4,
            "Charmander",
            listOf(PokemonType("Fire"))
        )

        val pokemons = listOf(Charmander, Charmander, Charmander)

        val recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokemonAdapter(pokemons)
    }
}