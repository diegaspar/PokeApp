package com.diegaspar.pokemonlist.presentation.state

import com.diegaspar.pokemonlist.presentation.model.PokemonUI

sealed class PokemonListState(val list: List<PokemonUI>) {
    object InitialLoadingState : PokemonListState(emptyList())
    data class ErrorState(val throwable: Throwable, val pokemonList: List<PokemonUI>) :
        PokemonListState(pokemonList)
    data class SuccessState(val pokemonList: List<PokemonUI>) : PokemonListState(pokemonList)
    data class SuccessLoadingMoreItemsState(val pokemonList: List<PokemonUI>) :
        PokemonListState(pokemonList)
}