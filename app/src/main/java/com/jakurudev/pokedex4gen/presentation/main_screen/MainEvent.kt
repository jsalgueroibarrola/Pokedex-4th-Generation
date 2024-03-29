package com.jakurudev.pokedex4gen.presentation.main_screen

import com.jakurudev.pokedex4gen.domain.model.Pokemon

sealed class MainEvent {
    data class DisplayPokemon(val pokemon: Pokemon) : MainEvent()
}


