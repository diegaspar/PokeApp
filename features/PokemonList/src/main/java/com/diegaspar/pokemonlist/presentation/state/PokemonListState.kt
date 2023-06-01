package com.diegaspar.pokemonlist.presentation.state

import com.diegaspar.pokemonlist.presentation.model.PokemonUI

sealed class PokemonListState(
    val list: List<PokemonUI> = emptyList(),
    val hasNextPage: Boolean = true
) {
    object InitialLoadingState : PokemonListState()
    object ErrorStateEmptyList : PokemonListState()

    data class ErrorState(val throwable: Throwable, val pokemonList: List<PokemonUI>) :
        PokemonListState(pokemonList)

    data class SuccessState(val pokemonList: List<PokemonUI>, val nextPage: Boolean) :
        PokemonListState(pokemonList, nextPage)
}