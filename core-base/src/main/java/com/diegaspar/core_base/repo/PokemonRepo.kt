package com.diegaspar.core_base.repo

import com.diegaspar.core_base.domain.Pokemon

interface PokemonRepo {
    suspend fun getPokemons(offset: Int): List<Pokemon>
}