package com.jakurudev.pokedex4gen.navigation

sealed class AppScreens(val route: String) {
    object MainScreen : AppScreens("main_screen")
    object PokemonScreen : AppScreens("pokemon_screen")
}
