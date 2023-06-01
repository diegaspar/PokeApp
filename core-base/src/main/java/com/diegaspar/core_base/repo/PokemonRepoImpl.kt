package com.diegaspar.core_base.repo

import com.diegaspar.core_base.data.PokemonRemoteDataSource
import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.core_base.domain.PokemonListPaginated

class PokemonRepoImpl(private val remoteDataSource: PokemonRemoteDataSource) : PokemonRepo {
    override suspend fun getPokemons(offset: Int): PokemonListPaginated =
        remoteDataSource.getPokemons(offset = offset)

    override suspend fun getPokemonDetail(id: String): PokemonDetail =
        remoteDataSource.getPokemonDetail(id = id)
}