package com.jakurudev.pokedex4gen.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jakurudev.pokedex4gen.data.PokemonService
import com.jakurudev.pokedex4gen.data.mapper.toPokemon
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

class PokemonPagingSource(
    private val pokemonService: PokemonService
) : PagingSource<Int, Pokemon>() {

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { position ->
            val anchorPage = state.closestPageToPosition(position)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val position = params.key ?: 387

            val list = mutableListOf<Pokemon>()
            coroutineScope {
                (position until (position + params.loadSize).coerceAtMost(491)).map { id ->
                    async(Dispatchers.IO) {
                        val response = pokemonService.getPokemon(id = id.toLong())
                        if (response.isSuccessful) {
                            response.body()?.let {
                                it.toPokemon()?.let { pokemon -> list.add(pokemon) }
                            }
                        }
                    }
                }.awaitAll()
            }

            list.sortBy { it.id }

            LoadResult.Page(
                data = list,
                prevKey = if (position == 387) null else position - params.loadSize,
                nextKey = if (list.isEmpty() || position + params.loadSize >= 490) null else position + params.loadSize
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}