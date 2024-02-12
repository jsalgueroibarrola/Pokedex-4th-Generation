package com.jakurudev.pokedex4gen.data.response


import com.google.gson.annotations.SerializedName

data class PokemonTypeResponse(
    @SerializedName("slot")
    val slot: Int?,
    @SerializedName("type")
    val type: TypeResponse?
)