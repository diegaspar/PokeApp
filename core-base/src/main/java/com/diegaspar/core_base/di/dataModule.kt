package com.diegaspar.core_base.di

import com.diegaspar.core_base.data.persistence.PokemonLocalDataSource
import com.diegaspar.core_base.data.persistence.PokemonLocalDataSourceImpl
import com.diegaspar.core_base.data.persistence.mapper.PokemonEntityMapper
import com.diegaspar.core_base.data.remote.PokemonRemoteDataSource
import com.diegaspar.core_base.data.remote.PokemonRemoteDataSourceImpl
import com.diegaspar.core_base.repo.PokemonRepo
import com.diegaspar.core_base.repo.PokemonRepoImpl
import org.koin.dsl.module

val dataModule = module {
    single<PokemonRemoteDataSource> { PokemonRemoteDataSourceImpl(get()) }
    single<PokemonLocalDataSource> { PokemonLocalDataSourceImpl(get(), get()) }
    single<PokemonRepo> { PokemonRepoImpl(get(), get(), get()) }
    single { PokemonEntityMapper() }
}