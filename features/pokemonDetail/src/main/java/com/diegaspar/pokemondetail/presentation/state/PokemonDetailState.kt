package com.diegaspar.pokemondetail.presentation.state

import com.diegaspar.pokemondetail.presentation.model.PokemonDetailUI

sealed class PokemonDetailState {
    object InitialLoadingState : PokemonDetailState()
    data class ErrorState(val throwable: Throwable) : PokemonDetailState()
    data class SuccessState(
        val pokemonDetail: PokemonDetailUI,
        val mainColor: Int? = null,
    ) : PokemonDetailState()
}
