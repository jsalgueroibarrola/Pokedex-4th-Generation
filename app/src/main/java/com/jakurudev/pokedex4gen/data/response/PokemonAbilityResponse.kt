package com.jakurudev.pokedex4gen.data.response


import com.google.gson.annotations.SerializedName

data class PokemonAbilityResponse(
    @SerializedName("ability")
    val abilityResponse: AbilityResponse,
    @SerializedName("is_hidden")
    val isHidden: Boolean,
    @SerializedName("slot")
    val slot: Int
)