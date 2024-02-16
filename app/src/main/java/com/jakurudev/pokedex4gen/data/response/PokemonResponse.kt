package com.jakurudev.pokedex4gen.data.response

import androidx.compose.ui.text.font.FontWeight
import com.google.gson.annotations.SerializedName

data class PokemonResponse(
    @SerializedName("abilities")
    val abilities: List<PokemonAbilityResponse>,
    @SerializedName("base_experience")
    val baseExperience: Int?,
    @SerializedName("cries")
    val cries: Any?,
    @SerializedName("forms")
    val forms: Any?,
    @SerializedName("game_indices")
    val gameIndices: Any?,
    @SerializedName("height")
    val heightDecimeter: Int,
    @SerializedName("held_items")
    val heldItems: Any?,
    @SerializedName("id")
    val id: Long,
    @SerializedName("location_area_encounters")
    val locationAreaEncounters: String?,
    @SerializedName("is_default")
    val isDefault: Boolean?,
    @SerializedName("moves")
    val moves: Any?,
    @SerializedName("name")
    val name: String,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("past_abilities")
    val pastAbilities: Any?,
    @SerializedName("past_types")
    val pastType: Any?,
    @SerializedName("species")
    val species: Any?,
    @SerializedName("sprites")
    val sprites: SpritesResponse,
    @SerializedName("stats")
    val stats: List<PokemonStatResponse>,
    @SerializedName("types")
    val types: List<PokemonTypeResponse>,
    @SerializedName("weight")
    val weightHectogram: Int
    )
