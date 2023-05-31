package com.diegaspar.pokeapp

import android.app.Application
import org.koin.core.context.startKoin

class PokeAppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupKoin()
    }

    private fun setupKoin() {
        startKoin {
            modules()
        }
    }
}