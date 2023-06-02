package com.diegaspar.pokeapp.application

import android.app.Application
import com.diegaspar.core_base.di.dataModule
import com.diegaspar.core_base.di.networkModule
import com.diegaspar.core_base.di.persistenceModule
import com.diegaspar.pokemondetail.di.pokemonDetailFeatureModule
import com.diegaspar.pokemonlist.di.pokemonListFeatureModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PokeAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            androidContext(this@PokeAppApplication)
            modules(
                dataModule,
                networkModule,
                persistenceModule,
                pokemonListFeatureModule,
                pokemonDetailFeatureModule
            )
        }
    }
}