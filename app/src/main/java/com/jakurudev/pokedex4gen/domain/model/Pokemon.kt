package com.jakurudev.pokedex4gen.domain.model

data class Pokemon(
    val id: Long,
    val name: String,
    val sprites: Sprites,
    val types: List<String>,
    val stats: List<Stat>,
    val abilities: List<Ability>,
    val weightHectogram: Int,
    val heightDecimeter: Int,
    var isDisplay: Boolean = false
)

fun Pokemon.weightToKg(): Double {
    return weightHectogram * 0.1
}

fun Pokemon.weightToPounds(): Double {
    return weightHectogram * 0.220462
}

fun Pokemon.weightToOunces(): Double {
    return weightHectogram * 3.5274
}

fun Pokemon.heightToDecimeter(): Double {
    return heightDecimeter.toDouble() / 10
}

fun Pokemon.heightToFeet(): Double {
    return heightDecimeter * 0.328084
}