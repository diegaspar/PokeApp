package com.diegaspar.pokeapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diegaspar.pokemondetail.presentation.ui.PokemonDetailScreen
import com.diegaspar.pokemondetail.presentation.ui.idArgument
import com.diegaspar.pokemonlist.presentation.view.PokemonListScreen

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MainNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.PokemonListScreen.route
    ) {
        composable(
            route = NavigationRoutes.PokemonListScreen.route
        ) {
            PokemonListScreen(onNavigateToPokemonDetail = { pokemonId ->
                navController.navigate(NavigationRoutes.PokemonDetailScreen.createRute(pokemonId))
            })
        }
        composable(
            route = NavigationRoutes.PokemonDetailScreen.route
        ) { backStackEntry ->
            PokemonDetailScreen(backStackEntry.arguments?.getString(idArgument).orEmpty())
        }
    }

}