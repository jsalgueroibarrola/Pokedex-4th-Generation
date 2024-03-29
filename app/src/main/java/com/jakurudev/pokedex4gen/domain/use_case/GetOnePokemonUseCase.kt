package com.jakurudev.pokedex4gen.domain.use_case

import com.jakurudev.pokedex4gen.domain.model.ApiResponse
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.repository.PokemonRepository

class GetOnePokemonUseCase(private val repository: PokemonRepository) {

    suspend operator fun invoke(id: Long): ApiResponse<Pokemon> {
        return repository.getPokemon(id = id)
    }
}