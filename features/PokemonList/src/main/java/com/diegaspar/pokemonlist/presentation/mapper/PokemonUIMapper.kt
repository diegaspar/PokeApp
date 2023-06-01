package com.diegaspar.pokemonlist.presentation.mapper

import com.diegaspar.core_base.domain.Pokemon
import com.diegaspar.pokemonlist.presentation.model.PokemonUI

class PokemonUIMapper {
    fun mapFromDomain(pokemonDomain: Pokemon, index: Int = 0) =
        PokemonUI(
            name = pokemonDomain.name,
            index = index.toString(),
            url = pokemonDomain.url
        )

    fun addIndexNumber(index: Int, pokemonUI: PokemonUI) =
        pokemonUI.copy(index = index.toString())
}