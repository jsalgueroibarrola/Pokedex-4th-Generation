package com.jakurudev.pokedex4gen.data

import com.jakurudev.pokedex4gen.data.response.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonService {

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Long
    ): Response<PokemonResponse>
}
