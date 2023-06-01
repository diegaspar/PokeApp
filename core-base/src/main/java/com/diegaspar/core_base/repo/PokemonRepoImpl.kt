package com.diegaspar.core_base.repo

import com.diegaspar.core_base.data.PokemonRemoteDataSource
import com.diegaspar.core_base.domain.Pokemon

class PokemonRepoImpl(private val remoteDataSource: PokemonRemoteDataSource) : PokemonRepo {
    override suspend fun getPokemons(offset: Int): List<Pokemon> =
        remoteDataSource.getPokemons(offset = offset)
}