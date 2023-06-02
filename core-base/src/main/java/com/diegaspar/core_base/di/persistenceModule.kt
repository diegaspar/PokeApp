package com.diegaspar.core_base.di

import android.app.Application
import androidx.room.Room
import com.diegaspar.core_base.data.persistence.appdatabase.PokemonDatabase
import com.diegaspar.core_base.data.persistence.dao.PokemonDao
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

private const val dataBaseName = "pokemon_database"

val persistenceModule = module {
    fun provideDatabase(application: Application): PokemonDatabase {
        return Room.databaseBuilder(
            application,
            PokemonDatabase::class.java,
            dataBaseName
        ).build()
    }

    fun providePokemonDao(database: PokemonDatabase): PokemonDao {
        return database.pokemonDao()
    }

    single { provideDatabase(androidApplication()) }
    single { providePokemonDao(get()) }
}