package com.diegaspar.core_base.repo

import com.diegaspar.core_base.data.persistence.PokemonLocalDataSource
import com.diegaspar.core_base.data.persistence.mapper.PokemonEntityMapper
import com.diegaspar.core_base.data.remote.PokemonRemoteDataSource
import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.core_base.domain.PokemonListPaginated

class PokemonRepoImpl(
    private val remoteDataSource: PokemonRemoteDataSource,
    private val localDataSource: PokemonLocalDataSource,
    private val mapper: PokemonEntityMapper
) : PokemonRepo {
    override suspend fun getPokemons(offset: Int): PokemonListPaginated =
        remoteDataSource.getPokemons(offset = offset)

    override suspend fun getPokemonDetail(id: String): PokemonDetail {
        return try {
            val pokemonDetail = remoteDataSource.getPokemonDetail(id = id)
            localDataSource.insertPokemon(pokemonDetail, id)
            pokemonDetail
        } catch (exception: Exception) {
            try {
                mapper.mapFromEntity(localDataSource.retrievePokemon(id))
            } catch (e: Exception) {
                throw Exception("Pokemon not found for id: $id", e)
            }
        }
    }
}