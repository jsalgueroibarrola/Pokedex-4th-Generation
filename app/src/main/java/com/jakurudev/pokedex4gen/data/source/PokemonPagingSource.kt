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

    companion object {
        const val NUMBER_FIRST_POKEMON = 387
        const val NUMBER_LAST_POKEMON = 490
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val position = params.key ?: NUMBER_FIRST_POKEMON

            val list = mutableListOf<Pokemon>()
            coroutineScope {
                (position until (position + params.loadSize).coerceAtMost(NUMBER_LAST_POKEMON + 1)).map { id ->
                    async(Dispatchers.IO) {
                        val response = pokemonService.getPokemon(id = id.toLong())
                        if (response.isSuccessful) {
                            response.body()?.let {
                                it.toPokemon().let { pokemon -> list.add(pokemon) }
                            }
                        }
                    }
                }.awaitAll()
            }

            list.sortBy { it.id }

            LoadResult.Page(
                data = list,
                prevKey = if (position == NUMBER_FIRST_POKEMON) null else position - params.loadSize,
                nextKey = if (list.isEmpty() || position + params.loadSize >= NUMBER_LAST_POKEMON) null else position + params.loadSize
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}