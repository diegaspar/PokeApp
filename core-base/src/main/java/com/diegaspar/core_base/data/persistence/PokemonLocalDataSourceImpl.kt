package com.diegaspar.core_base.data.persistence

import com.diegaspar.core_base.data.persistence.dao.PokemonDao
import com.diegaspar.core_base.data.persistence.mapper.PokemonEntityMapper
import com.diegaspar.core_base.data.persistence.model.PokemonEntity
import com.diegaspar.core_base.domain.PokemonDetail

class PokemonLocalDataSourceImpl(
    private val dao: PokemonDao,
    private val pokemonEntityMapper: PokemonEntityMapper
) : PokemonLocalDataSource {
    override suspend fun insertPokemon(pokemon: PokemonDetail, id: String) =
        dao.insert(pokemonEntityMapper.mapFromDomain(pokemon, id))

    override suspend fun retrievePokemon(id: String): PokemonEntity =
        dao.findById(id)
}