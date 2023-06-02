package com.diegaspar.core_base.data.remote

import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.core_base.domain.PokemonListPaginated

interface PokemonRemoteDataSource {
    suspend fun getPokemons(offset: Int): PokemonListPaginated
    suspend fun getPokemonDetail(id: String): PokemonDetail
}