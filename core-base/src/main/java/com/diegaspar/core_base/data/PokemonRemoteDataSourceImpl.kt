package com.diegaspar.core_base.data

import com.diegaspar.core_base.api.PokemonApiService
import com.diegaspar.core_base.api.model.toPokemonDetail
import com.diegaspar.core_base.domain.PaginationData
import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.core_base.domain.PokemonListPaginated

class PokemonRemoteDataSourceImpl(private val service: PokemonApiService) :
    PokemonRemoteDataSource {

    override suspend fun getPokemons(offset: Int): PokemonListPaginated {
        val paginatedDataFromService = service.getPokemons(offset = offset)
        return PokemonListPaginated(
            pokemonList = paginatedDataFromService.results.map { it.toDomain() },
            pageData = PaginationData(
                hasNextPage = paginatedDataFromService.nextUrlPage.isNullOrEmpty().not()
            )
        )
    }

    override suspend fun getPokemonDetail(id: String): PokemonDetail =
        service.getPokemonDetail(pokemonId = id).toPokemonDetail()
}