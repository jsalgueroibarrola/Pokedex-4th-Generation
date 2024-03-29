package com.jakurudev.pokedex4gen.presentation.pokemon_screen

sealed class PokemonEvent {
    data class GetPokemon(val id: Long) : PokemonEvent()
}

