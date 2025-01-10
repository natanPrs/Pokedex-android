package com.prs.pokedex_android.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.prs.pokedex_android.R
import com.prs.pokedex_android.api.PokemonRepository
import com.prs.pokedex_android.domain.Pokemon

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        recyclerView = findViewById<RecyclerView>(R.id.rvPokemons)

//        val Charmander = Pokemon(
//            "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/004.png",
//            4,
//            "Charmander",
//            listOf(PokemonType("Fire"))
//        )
//
//        val pokemons = listOf(Charmander, Charmander, Charmander)

        Thread(Runnable {
            loadPokemons()

        }).start()

    }

    private fun loadPokemons() {
        val pokemonApiResult = PokemonRepository.listPokemons()

        pokemonApiResult?.results?.let {
            val pokemon: List<Pokemon?> = it.map { pokemonResult ->

                val number = pokemonResult.url
                    .replace("https://pokeapi.co/api/v2/pokemon/", "")
                    .replace("/", "").toInt()

                val pokemonApiResult = PokemonRepository.getPokemon(number)

                pokemonApiResult?.let {
                    Pokemon(
                        pokemonApiResult.id,
                        pokemonApiResult.name,
                        pokemonApiResult.types.map { type ->
                            type.type
                        }
                    )
                }

            }

            val layoutManager = LinearLayoutManager(this)

            recyclerView.post {
                recyclerView.layoutManager = layoutManager
                recyclerView.adapter = PokemonAdapter(pokemon)
            }

        }
    }
}