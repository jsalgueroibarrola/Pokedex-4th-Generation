package com.jakurudev.pokedex4gen.data.mapper

import com.jakurudev.pokedex4gen.data.response.SpritesResponse
import com.jakurudev.pokedex4gen.domain.model.Sprites

fun SpritesResponse.toSprites(): Sprites {
    return Sprites(
        backDefault = backDefault,
        backFemale = backFemale,
        backShiny = backShiny,
        backShinyFemale = backShinyFemale,
        frontDefault = frontDefault,
        frontFemale = frontFemale,
        frontShiny = frontShiny,
        frontShinyFemale = frontShinyFemale
    )
}