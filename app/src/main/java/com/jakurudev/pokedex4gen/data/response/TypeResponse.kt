package com.jakurudev.pokedex4gen.data.response


import com.google.gson.annotations.SerializedName

data class TypeResponse(
    @SerializedName("name")
    val name: String,
    @SerializedName("url")
    val url: String?
)