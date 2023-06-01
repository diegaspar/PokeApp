package com.diegaspar.pokemondetail.di

import com.diegaspar.pokemondetail.domain.GetPokemonDetailUseCase
import com.diegaspar.pokemondetail.presentation.mapper.PokemonDetailUIMapper
import com.diegaspar.pokemondetail.presentation.viewmodel.PokemonDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokemonDetailFeatureModule = module {
    viewModel { PokemonDetailViewModel(get(), get()) }
    single { PokemonDetailUIMapper() }
    single { GetPokemonDetailUseCase(get()) }
}