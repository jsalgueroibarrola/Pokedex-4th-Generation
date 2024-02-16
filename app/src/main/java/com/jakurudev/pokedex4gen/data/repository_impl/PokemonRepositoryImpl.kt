package com.jakurudev.pokedex4gen.data.repository_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jakurudev.pokedex4gen.data.PokemonService
import com.jakurudev.pokedex4gen.data.mapper.toPokemon
import com.jakurudev.pokedex4gen.data.source.PokemonPagingSource
import com.jakurudev.pokedex4gen.domain.model.ApiResponse
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow
import kotlin.Exception

class PokemonRepositoryImpl(private val pokemonService: PokemonService) : PokemonRepository {
    override fun getPokemonStream(pageSize: Int, prefetchDistance: Int): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 8, prefetchDistance = 6),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonService = pokemonService)
            }
        ).flow
    }

    override suspend fun getPokemon(id: Long): ApiResponse<Pokemon> {
        try {
            val response = pokemonService.getPokemon(id = id)
            if (response.isSuccessful) {
                response.body()?.let {
                    return ApiResponse.Success(it.toPokemon())
                }
                throw Exception("Pokemon Error")
            }
            throw Exception("Pokemon Not Found")
        } catch (e: Exception) {
            return ApiResponse.Error(e)
        }
    }
}