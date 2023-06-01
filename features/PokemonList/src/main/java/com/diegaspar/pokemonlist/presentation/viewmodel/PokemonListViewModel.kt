package com.diegaspar.pokemonlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.pokemonlist.domain.GetPokemonsUseCase
import com.diegaspar.pokemonlist.presentation.mapper.PokemonUIMapper
import com.diegaspar.pokemonlist.presentation.state.PokemonListState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonListViewModel(
    private val getPokemonsUseCase: GetPokemonsUseCase,
    private val pokemonUIMapper: PokemonUIMapper
) : ViewModel() {

    private val _uiState = MutableStateFlow<PokemonListState>(PokemonListState.InitialLoadingState)
    private fun getCurrentPokemonsList() = _uiState.value.list

    val uiState: StateFlow<PokemonListState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update { PokemonListState.ErrorState(throwable, getCurrentPokemonsList()) }
    }

    init {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getNewPokemons()
        }
    }

    fun getMorePokemons() {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            getNewPokemons()
        }
    }

    private suspend fun getNewPokemons() {
        _uiState.update {
            PokemonListState.SuccessState(
                getCurrentPokemonsList().plus(
                    getPokemonsUseCase.invoke(GetPokemonsUseCase.Params(offset = getCurrentPokemonsList().size))
                        .map { pokemon ->
                            this.pokemonUIMapper.mapFromDomain(pokemon)
                        }).mapIndexed { index, pokemonUI ->
                    pokemonUIMapper.addIndexNumber(
                        index,
                        pokemonUI
                    )
                }
            )
        }
    }
}

