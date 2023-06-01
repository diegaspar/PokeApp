package com.diegaspar.pokemondetail.presentation.mapper

import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.pokemondetail.presentation.model.PokemonDetailUI

class PokemonDetailUIMapper {
    fun mapFromDomain(pokemonDomain: PokemonDetail) =
        PokemonDetailUI(
            name = pokemonDomain.name,
            imageUrl = pokemonDomain.imageUrl,
            types = pokemonDomain.typesList
        )
}