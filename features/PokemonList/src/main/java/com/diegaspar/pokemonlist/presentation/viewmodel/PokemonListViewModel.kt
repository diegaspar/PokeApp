package com.diegaspar.pokemonlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.pokemonlist.domain.GetPokemonsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase
) : ViewModel() {


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
//        _liveState.postValue(ErrorState(throwable.toString()))
    }

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            val a = getPokemonsUseCase.invoke(GetPokemonsUseCase.Params())
            a.map {
                it
            }
        }
    }

    fun getMorePokemons() {
        //Retrive more pokemons
    }

}