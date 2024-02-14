package com.jakurudev.pokedex4gen.presentation.main_screen

import com.jakurudev.pokedex4gen.domain.model.Pokemon

data class MainState(
    val displayedPokemon: Pokemon? = null
)
