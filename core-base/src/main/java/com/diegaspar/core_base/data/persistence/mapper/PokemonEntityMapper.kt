package com.diegaspar.core_base.data.persistence.mapper

import com.diegaspar.core_base.data.persistence.model.PokemonEntity
import com.diegaspar.core_base.domain.PokemonDetail

class PokemonEntityMapper {
    fun mapFromDomain(pokemonDomain: PokemonDetail, id: String) =
        PokemonEntity(
            id = id,
            name = pokemonDomain.name,
            imageUrl = pokemonDomain.imageUrl,
            typeList = pokemonDomain.typesList
        )

    fun mapFromEntity(pokemonEntity: PokemonEntity) =
        PokemonDetail(
            name = pokemonEntity.name.orEmpty(),
            imageUrl = pokemonEntity.imageUrl.orEmpty(),
            typesList = pokemonEntity.typeList.orEmpty()
        )
}