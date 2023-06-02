package com.diegaspar.pokemondetail.presentation.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.drawable.toBitmap
import androidx.palette.graphics.Palette
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.diegaspar.core_ui.components.InitialLoading
import com.diegaspar.pokemondetail.R
import com.diegaspar.pokemondetail.presentation.model.PokemonDetailUI
import com.diegaspar.pokemondetail.presentation.state.PokemonDetailState
import com.diegaspar.pokemondetail.presentation.viewmodel.PokemonDetailViewModel
import org.koin.androidx.compose.koinViewModel

const val idArgument = "id"

@ExperimentalComposeUiApi
@Composable
fun PokemonDetailScreen(pokemonId: String, viewModel: PokemonDetailViewModel = koinViewModel()) {
    val pokemonDetailState by viewModel.uiState.collectAsState()

    when (pokemonDetailState) {
        is PokemonDetailState.ErrorState -> EmptyErrorState(pokemonId, viewModel)
        PokemonDetailState.InitialLoadingState -> {
            InitialLoading()
            viewModel.loadPokemonDetail(pokemonId)
        }

        is PokemonDetailState.SuccessState -> PokemonDetailed(
            (pokemonDetailState as PokemonDetailState.SuccessState).pokemonDetail,
            (pokemonDetailState as PokemonDetailState.SuccessState).mainColor,
            viewModel,
        )
    }
}

@Composable
fun PokemonDetailed(
    pokemonDetail: PokemonDetailUI,
    mainColor: Int?,
    viewModel: PokemonDetailViewModel
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (name, image, backgroundImage, types) = createRefs()
        Text(text = pokemonDetail.name,
            fontSize = 28.sp,
            color = Color.DarkGray,
            modifier = Modifier
                .constrainAs(name) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(bottom = 52.dp)
        )
        mainColor?.let {
            Box(modifier = Modifier
                .clip(shape = RoundedCornerShape(15.dp))
                .size(300.dp)

                .background(Color(mainColor).copy(alpha = 0.2f))
                .constrainAs(backgroundImage) {
                    top.linkTo(image.top)
                    bottom.linkTo(image.bottom)
                    start.linkTo(image.start)
                    end.linkTo(image.end)
                })
        }

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemonDetail.imageUrl)
                .crossfade(true)
                .allowHardware(false)
                .build(),
            placeholder = painterResource(id = R.drawable.placeholder),
            error = painterResource(id = R.drawable.placeholder),
            contentDescription = stringResource(R.string.content_description_pokemon_detail_image),
            modifier = Modifier
                .constrainAs(image) {
                    top.linkTo(name.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .size(260.dp)
                .padding(bottom = 24.dp),
            onSuccess = { it ->
                val palette = Palette.from(it.result.drawable.toBitmap()).generate()
                viewModel.onColorsLoaded(
                    mainColor = palette.dominantSwatch?.let { Color(it.rgb).toArgb() }
                        ?: Color.LightGray.toArgb()
                )
            },
        )

        Row(
            modifier = Modifier
                .fillMaxSize()
                .constrainAs(types) {
                    top.linkTo(backgroundImage.bottom, margin = 24.dp)
                    start.linkTo(backgroundImage.start)
                    end.linkTo(backgroundImage.end)
                },
            horizontalArrangement = Arrangement
                .spacedBy(
                    space = 24.dp,
                    alignment = Alignment.CenterHorizontally
                )
        ) {
            pokemonDetail.types.forEach { type ->
                mainColor?.let {
                    TypeText(type = type, color = Color(it))
                }
            }
        }

        createVerticalChain(name, image, chainStyle = ChainStyle.Packed)
    }

}

@Composable
fun EmptyErrorState(pokemonId: String, viewModel: PokemonDetailViewModel) {
    ErrorToast()
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.pikachuerror))
            LottieAnimation(
                composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .padding(12.dp)
                    .size(300.dp)
            )
            Text(
                text = stringResource(R.string.something_went_wrong_pokemon_detail),
                modifier = Modifier
                    .padding(12.dp),
                textAlign = TextAlign.Center
            )
            Button(onClick = { viewModel.loadPokemonDetail(pokemonId) }) {
                Text(stringResource(R.string.button_try_again))
            }
        }
    }
}

@Composable
private fun ErrorToast() {
    Toast.makeText(
        LocalContext.current,
        stringResource(R.string.error_retrieving_pokemon_detal),
        Toast.LENGTH_LONG
    ).show()
}


@Composable
fun TypeText(type: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(size = 20.dp))
            .background(color = color),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = type.uppercase(),
            color = Color.White,
            modifier = Modifier.padding(horizontal = 24.dp, vertical = 8.dp)
        )
    }
}