package com.diegaspar.pokemondetail.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.ExperimentalComposeUiApi
import com.diegaspar.pokemondetail.presentation.state.PokemonDetailState
import com.diegaspar.pokemondetail.presentation.viewmodel.PokemonDetailViewModel
import org.koin.androidx.compose.koinViewModel

@ExperimentalComposeUiApi
@Composable
fun PokemonDetailScreen(viewModel: PokemonDetailViewModel = koinViewModel()) {
    val pokemonDetailState by viewModel.uiState.collectAsState()

    when (pokemonDetailState) {
        is PokemonDetailState.ErrorState -> TODO()
        PokemonDetailState.InitialLoadingState -> TODO()
        is PokemonDetailState.SuccessState -> TODO()
    }
}