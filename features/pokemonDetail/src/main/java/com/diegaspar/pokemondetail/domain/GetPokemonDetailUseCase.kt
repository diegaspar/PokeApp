package com.diegaspar.pokemondetail.domain

import com.diegaspar.core_base.domain.PokemonDetail
import com.diegaspar.core_base.repo.PokemonRepo

class GetPokemonDetailUseCase(private val pokemonRepository: PokemonRepo) {
    suspend operator fun invoke(params: Params): PokemonDetail =
        pokemonRepository.getPokemonDetail(params.id)

    data class Params(val id: String)
}