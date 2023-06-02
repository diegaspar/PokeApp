package com.diegaspar.core_base.data.persistence

import com.diegaspar.core_base.data.persistence.model.PokemonEntity
import com.diegaspar.core_base.domain.PokemonDetail

interface PokemonLocalDataSource {
    suspend fun insertPokemon(pokemon: PokemonDetail, id: String)
    suspend fun retrievePokemon(id: String): PokemonEntity
}