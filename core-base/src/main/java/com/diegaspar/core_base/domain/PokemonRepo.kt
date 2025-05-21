package com.diegaspar.core_base.domain

import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.core_base.domain.PokemonListPaginated

interface PokemonRepo {
    suspend fun getPokemons(offset: Int): PokemonListPaginated
    suspend fun getPokemonDetail(id: String): PokemonDetail
}