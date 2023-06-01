package com.diegaspar.core_base.domain

data class PokemonDetail(
    val name: String,
    val imageUrl: String,
    val typesList: List<String>
)
