package com.diegaspar.pokemonlist.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.core_base.domain.PokemonListPaginated
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
    private fun hasNextPage() = _uiState.value.hasNextPage

    val uiState: StateFlow<PokemonListState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update {
            when (getCurrentPokemonsList().isEmpty()) {
                true -> PokemonListState.ErrorStateEmptyList
                false -> PokemonListState.ErrorState(throwable, getCurrentPokemonsList())
            }
        }
    }

    fun getPokemons() {
        if (hasNextPage()) {
            viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
                getNewPokemons()
            }
        }
    }

    private suspend fun getNewPokemons() {
        val pokemonsSuccessResponse =
            getPokemonsUseCase.invoke(GetPokemonsUseCase.Params(offset = getCurrentPokemonsList().size))
        _uiState.update {
            updateUISuccessState(pokemonsSuccessResponse)
        }
    }

    private fun updateUISuccessState(response: PokemonListPaginated) =
        PokemonListState.SuccessState(
            pokemonList = getCurrentPokemonsList().plus(
                response.pokemonList
                    .map { pokemon ->
                        pokemonUIMapper.mapFromDomain(pokemon)
                    }).mapIndexed { index, pokemonUI ->
                pokemonUIMapper.addIndexNumber(
                    index + 1,
                    pokemonUI
                )
            },
            nextPage = response.pageData.hasNextPage
        )
}

