package com.jakurudev.pokedex4gen.data.mapper

import com.jakurudev.pokedex4gen.data.response.PokemonStatResponse
import com.jakurudev.pokedex4gen.domain.model.Stat

fun PokemonStatResponse.toStat(): Stat {
    return Stat(baseStat = baseStat, effort = effort, name = stat.name)
}