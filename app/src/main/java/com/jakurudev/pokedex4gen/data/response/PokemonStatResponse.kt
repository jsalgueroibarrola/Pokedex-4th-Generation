package com.jakurudev.pokedex4gen.data.response


import com.google.gson.annotations.SerializedName

data class PokemonStatResponse(
    @SerializedName("base_stat")
    val baseStat: Int?,
    @SerializedName("effort")
    val effort: Int?,
    @SerializedName("stat")
    val stat: StatResponse?
)