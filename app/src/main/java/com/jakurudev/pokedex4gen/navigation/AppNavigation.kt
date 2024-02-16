package com.jakurudev.pokedex4gen.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.paging.compose.collectAsLazyPagingItems
import com.jakurudev.pokedex4gen.presentation.main_screen.MainScreen
import com.jakurudev.pokedex4gen.presentation.main_screen.MainViewModel
import com.jakurudev.pokedex4gen.presentation.pokemon_screen.PokemonScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.PokemonScreen.route) {
        composable(route = AppScreens.MainScreen.route) {
            val viewModel = hiltViewModel<MainViewModel>()
            val lazyPokemonItems = viewModel.pokemonFlow.collectAsLazyPagingItems()
            val state = viewModel.state.collectAsState()
            MainScreen(
                lazyPokemonItems = lazyPokemonItems,
                state = state,
                onEvent = viewModel::onEvent,
                navController = navController
            )
        }
        composable(route = AppScreens.PokemonScreen.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.LongType })) {
            val id = it.arguments?.getString("id")
            Log.d("prueba", id.toString())
            PokemonScreen()
        }
    }
}


