package com.diegaspar.pokemonlist.presentation.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.diegaspar.core_ui.components.InitialLoading
import com.diegaspar.pokemonlist.R
import com.diegaspar.pokemonlist.presentation.extensions.isScrolledTo80PerCent
import com.diegaspar.pokemonlist.presentation.model.PokemonUI
import com.diegaspar.pokemonlist.presentation.state.PokemonListState
import com.diegaspar.pokemonlist.presentation.viewmodel.PokemonListViewModel
import org.koin.androidx.compose.koinViewModel

@ExperimentalComposeUiApi
@Composable
fun PokemonListScreen(
    onNavigateToPokemonDetail: (id: String) -> Unit,
    viewModel: PokemonListViewModel = koinViewModel()
) {
    val pokemonListState by viewModel.uiState.collectAsState()
    when (pokemonListState) {
        PokemonListState.InitialLoadingState -> {
            InitialLoading()
            viewModel.getPokemons()
        }
        PokemonListState.ErrorStateEmptyList -> EmptyErrorState(viewModel)
        is PokemonListState.ErrorState -> ErrorMessage(
            (pokemonListState as PokemonListState.ErrorState).pokemonList,
            viewModel, onNavigateToPokemonDetail
        )


        is PokemonListState.SuccessState -> PokemonList(
            (pokemonListState as PokemonListState.SuccessState).pokemonList,
            viewModel, onNavigateToPokemonDetail
        )

    }
}


@Composable
fun EmptyErrorState(viewModel: PokemonListViewModel) {
    ErrorToast()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.errordiglett))
            LottieAnimation(
                composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.padding(12.dp)
            )
            Text(
                text = stringResource(R.string.something_went_wrong_pokemons_list),
                modifier = Modifier.padding(12.dp)
            )
            Button(onClick = { viewModel.getPokemons() }) {
                Text(stringResource(R.string.button_try_again))
            }
        }
    }
}

@Composable
fun PokemonList(
    pokemonList: List<PokemonUI>,
    viewModel: PokemonListViewModel,
    onNavigateToPokemonDetail: (id: String) -> Unit
) {
    val scrollState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(modifier = Modifier.fillMaxSize(), state = scrollState) {
            items(pokemonList, itemContent = {
                PokemonRow(it) { onNavigateToPokemonDetail(it.id) }
            })
        }
    }

    val endOfListReached by remember {
        derivedStateOf {
            scrollState.isScrolledTo80PerCent()
        }
    }

    LaunchedEffect(endOfListReached) {
        viewModel.getPokemons()
    }

}

@Composable
private fun PokemonRow(it: PokemonUI, onNavigateToPokemonDetail: () -> Unit) {
    ConstraintLayout(
        modifier = Modifier
            .background(Color.White)
            .clickable {
                onNavigateToPokemonDetail()
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
        Text(text = it.name,
            color = Color.Black,
            modifier = Modifier
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
fun ErrorMessage(
    pokemonList: List<PokemonUI>,
    viewModel: PokemonListViewModel,
    onNavigateToPokemonDetail: (id: String) -> Unit
) {
    PokemonList(
        pokemonList = pokemonList, viewModel = viewModel,
        onNavigateToPokemonDetail
    )
    ErrorToast()
}

@Composable
private fun ErrorToast() {
    Toast.makeText(
        LocalContext.current,
        stringResource(R.string.error_retrieving_pokemons_list),
        Toast.LENGTH_LONG
    ).show()
}