package com.prs.pokedex_android.domain

data class Pokemon(
    val number: Int,
    val name: String,
    val types: List<PokemonType>
) {

    val formatedName = name.replaceFirstChar { it.uppercase() }
    val formatedNumber = number.toString().padStart(3, '0')

    val imageUrl =
        "https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/detail/$formatedNumber.png"
}

