package com.diegaspar.pokemondetail.presentation.mapper

import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.pokemondetail.presentation.model.PokemonDetailUI
import java.util.Locale

class PokemonDetailUIMapper {
    fun mapFromDomain(pokemonDomain: PokemonDetail) =
        PokemonDetailUI(
            name = pokemonDomain.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() },
            imageUrl = pokemonDomain.imageUrl,
            types = pokemonDomain.typesList
        )
}