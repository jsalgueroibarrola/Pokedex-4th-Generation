package com.jakurudev.pokedex4gen.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val sprites: Sprites,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<Ability>
)
