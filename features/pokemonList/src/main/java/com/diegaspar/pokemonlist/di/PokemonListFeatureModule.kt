package com.diegaspar.pokemonlist.di

import com.diegaspar.pokemonlist.domain.GetPokemonsUseCase
import com.diegaspar.pokemonlist.presentation.mapper.PokemonUIMapper
import com.diegaspar.pokemonlist.presentation.viewmodel.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val pokemonListFeatureModule = module {
    viewModel { PokemonListViewModel(get(), get()) }
    single { PokemonUIMapper() }
    single { GetPokemonsUseCase(get()) }
}