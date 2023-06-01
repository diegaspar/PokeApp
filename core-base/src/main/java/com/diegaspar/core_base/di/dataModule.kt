package com.diegaspar.core_base.di

import com.diegaspar.core_base.data.PokemonRemoteDataSource
import com.diegaspar.core_base.data.PokemonRemoteDataSourceImpl
import com.diegaspar.core_base.repo.PokemonRepo
import com.diegaspar.core_base.repo.PokemonRepoImpl
import org.koin.dsl.module

val  dataModule = module {
    single<PokemonRemoteDataSource> { PokemonRemoteDataSourceImpl(get()) }
    single<PokemonRepo> { PokemonRepoImpl(get()) }
}