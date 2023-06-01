package com.diegaspar.pokemonlist.presentation.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.diegaspar.pokemonlist.presentation.viewmodel.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel = koinViewModel()) {

    Box(modifier = Modifier.fillMaxSize()) {
        PokemonList(viewModel)
    }
    viewModel.getMorePokemons()
}

@Composable
fun PokemonList(viewModel: PokemonListViewModel) {

}