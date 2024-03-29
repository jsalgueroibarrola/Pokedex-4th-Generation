package com.jakurudev.pokedex4gen.domain.use_case

import androidx.paging.PagingData
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.repository.PokemonRepository
import kotlinx.coroutines.flow.Flow

class GetPokemonsUseCase(private val repository: PokemonRepository) {

    operator fun invoke(pageSize: Int = 8, prefetchDistance: Int = 6): Flow<PagingData<Pokemon>> {
        return repository.getPokemonStream(pageSize = pageSize, prefetchDistance = prefetchDistance)
    }
}