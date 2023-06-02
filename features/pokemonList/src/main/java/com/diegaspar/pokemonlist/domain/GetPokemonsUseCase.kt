package com.diegaspar.pokemonlist.domain

import com.diegaspar.core_base.domain.PokemonListPaginated
import com.diegaspar.core_base.repo.PokemonRepo

class GetPokemonsUseCase(private val pokemonRepository: PokemonRepo) {
    suspend operator fun invoke(params: Params): PokemonListPaginated =
        pokemonRepository.getPokemons(params.offset)

    data class Params(val offset: Int = 0)
}