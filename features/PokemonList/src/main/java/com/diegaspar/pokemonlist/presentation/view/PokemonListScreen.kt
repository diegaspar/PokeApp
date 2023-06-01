package com.diegaspar.pokemonlist.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.diegaspar.pokemonlist.presentation.extensions.isScrolledTo80PerCent
import com.diegaspar.pokemonlist.presentation.extensions.isScrolledToEnd
import com.diegaspar.pokemonlist.presentation.model.PokemonUI
import com.diegaspar.pokemonlist.presentation.state.PokemonListState
import com.diegaspar.pokemonlist.presentation.viewmodel.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

@ExperimentalComposeUiApi
@Composable
fun PokemonListScreen(viewModel: PokemonListViewModel = koinViewModel()) {

    val pokemonListState by viewModel.uiState.collectAsState()
    when (pokemonListState) {
        is PokemonListState.ErrorState -> ErrorMessage()
        PokemonListState.InitialLoadingState -> InitialLoading()
        is PokemonListState.SuccessLoadingMoreItemsState -> InitialLoading()
        is PokemonListState.SuccessState -> PokemonList(
            (pokemonListState as PokemonListState.SuccessState).pokemonList,
            viewModel
        )
    }
}

@Composable
fun PokemonList(pokemonList: List<PokemonUI>, viewModel: PokemonListViewModel) {
    val scrollState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState) {
            items(pokemonList, itemContent = {
                PokemonRow(it)
            })
        }
    }

    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledTo80PerCent()
        }
    }

    LaunchedEffect(endOfListReached) {
        viewModel.getMorePokemons()
    }

}

@Composable
private fun PokemonRow(it: PokemonUI) {
    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .clickable {

            }
    ) {
        val (name, id, divider) = createRefs()
        Text(text = "# ${it.index}",
            color = Color.Gray,
            modifier = Modifier
                .constrainAs(id) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .padding(horizontal = 8.dp, vertical = 12.dp))
        Text(text = it.name, modifier = Modifier
            .constrainAs(name) {
                start.linkTo(id.end)
                top.linkTo(id.top)
                bottom.linkTo(id.bottom)
            })
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier
                .constrainAs(divider) {
                    start.linkTo(parent.start)
                    top.linkTo(id.bottom)
                }

        )
    }
}

@Composable
fun InitialLoading() {
    ConstraintLayout(Modifier.fillMaxSize()) {
        val (initialLoading) = createRefs()
        Box(modifier = Modifier.constrainAs(initialLoading) {
            top.linkTo(parent.top)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }) {
            CircularProgressIndicator()
        }
    }

}

@Composable
fun ErrorMessage() {

}