package com.jakurudev.pokedex4gen.data.repository_impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jakurudev.pokedex4gen.data.PokemonService
import com.jakurudev.pokedex4gen.data.source.PokemonPagingSource
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class PokemonRepositoryImpl(private val pokemonService: PokemonService) : PokemonRepository {
    override fun getPokemonStream(pageSize: Int, prefetchDistance: Int): Flow<PagingData<Pokemon>> {
        return Pager(
            config = PagingConfig(pageSize = 8, prefetchDistance = 6),
            pagingSourceFactory = {
                PokemonPagingSource(pokemonService = pokemonService)
            }
        ).flow
    }
}