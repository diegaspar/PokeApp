package com.diegaspar.pokeapp.navigation

sealed class NavigationRoutes(val route: String) {
    object PokemonListScreen : NavigationRoutes("pokemonListScreen")
    object PokemonDetailScreen : NavigationRoutes("pokemonDetailScreen/{id}") {
        fun createRute(id: String) = "pokemonDetailScreen/$id"
    }
}
