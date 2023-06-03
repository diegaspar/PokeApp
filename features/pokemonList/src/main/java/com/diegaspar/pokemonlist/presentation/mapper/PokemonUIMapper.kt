package com.diegaspar.pokemonlist.presentation.mapper

import com.diegaspar.core_base.domain.Pokemon
import com.diegaspar.pokemonlist.presentation.model.PokemonUI
import java.util.Locale

class PokemonUIMapper {
    fun mapFromDomain(pokemonDomain: Pokemon, index: Int = 0) =
        PokemonUI(
            name = pokemonDomain.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            index = index.toString(),
            url = pokemonDomain.url,
            id = extractIdFromUrl(pokemonDomain)
        )

    fun addIndexNumber(index: Int, pokemonUI: PokemonUI) =
        pokemonUI.copy(index = index.toString())

    fun extractIdFromUrl(pokemonDomain: Pokemon) =
        pokemonDomain.url.trimEnd('/').split("/").last()
}