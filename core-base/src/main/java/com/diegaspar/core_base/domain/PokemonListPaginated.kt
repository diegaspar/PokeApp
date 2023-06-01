package com.diegaspar.core_base.domain

data class PokemonListPaginated(
    val pokemonList: List<Pokemon>,
    val pageData: PaginationData
)
