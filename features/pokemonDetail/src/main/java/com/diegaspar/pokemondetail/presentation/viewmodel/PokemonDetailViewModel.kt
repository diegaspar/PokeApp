package com.diegaspar.pokemondetail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diegaspar.pokemondetail.domain.GetPokemonDetailUseCase
import com.diegaspar.pokemondetail.presentation.mapper.PokemonDetailUIMapper
import com.diegaspar.pokemondetail.presentation.state.PokemonDetailState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val getPokemonDetailUseCase: GetPokemonDetailUseCase,
    private val pokemonDetailUIMapper: PokemonDetailUIMapper
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<PokemonDetailState>(PokemonDetailState.InitialLoadingState)

    val uiState: StateFlow<PokemonDetailState> = _uiState.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _uiState.update {
            PokemonDetailState.ErrorState(throwable)
        }
    }

    fun loadPokemonDetail(id: String) {
        viewModelScope.launch(Dispatchers.IO + exceptionHandler) {
            _uiState.update {
                getPokemonFromUseCase(id)
            }
        }

    }

    fun onColorsLoaded(mainColor: Int) {
        _uiState.update {
            (_uiState.value as PokemonDetailState.SuccessState)
                .copy(
                    mainColor = mainColor
                )
        }
    }

    private suspend fun getPokemonFromUseCase(id: String) = PokemonDetailState.SuccessState(
        pokemonDetailUIMapper.mapFromDomain(
            getPokemonDetailUseCase.invoke(GetPokemonDetailUseCase.Params(id))
        )
    )
}