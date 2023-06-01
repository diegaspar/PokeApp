package com.diegaspar.core_base.data

import com.diegaspar.core_base.api.PokemonApiService

class PokemonRemoteDataSourceImpl(private val service: PokemonApiService) :
    PokemonRemoteDataSource {

    override suspend fun getPokemons(offset: Int) =
        service.getPokemons(offset = offset).results.map {
            it.toDomain()
        }
}