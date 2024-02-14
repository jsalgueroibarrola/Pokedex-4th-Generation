package com.jakurudev.pokedex4gen.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.jakurudev.pokedex4gen.presentation.main_screen.MainScreen
import com.jakurudev.pokedex4gen.presentation.main_screen.MainViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
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
    }
}


