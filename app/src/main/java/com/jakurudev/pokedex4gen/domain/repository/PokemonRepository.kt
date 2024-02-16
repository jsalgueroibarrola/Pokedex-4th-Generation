package com.jakurudev.pokedex4gen.domain.repository

import androidx.paging.PagingData
import com.jakurudev.pokedex4gen.domain.model.ApiResponse
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    fun getPokemonStream(pageSize: Int, prefetchDistance: Int): Flow<PagingData<Pokemon>>
    suspend fun getPokemon(id: Long) : ApiResponse<Pokemon>
}