package com.diegaspar.core_base.data

import com.diegaspar.core_base.domain.Pokemon

interface PokemonRemoteDataSource {
    suspend fun getPokemons(offset: Int): List<Pokemon>
}