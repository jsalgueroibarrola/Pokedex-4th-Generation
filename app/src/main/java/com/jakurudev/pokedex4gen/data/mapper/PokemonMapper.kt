package com.jakurudev.pokedex4gen.data.mapper

import com.jakurudev.pokedex4gen.data.response.PokemonResponse
import com.jakurudev.pokedex4gen.domain.model.Pokemon

fun PokemonResponse.toPokemon() : Pokemon {
    return Pokemon(
        id = id,
        name = name,
        sprites = sprites.toSprites(),
        types = types.map { it.name },
        stats = stats.map { it.toStat() },
        abilities = abilities.map { it.toAbility() },
        weightHectogram = weightHectogram,
        heightDecimeter = heightDecimeter
    )
}

// fun Pokemon.toPokemonResponse() : PokemonResponse {}