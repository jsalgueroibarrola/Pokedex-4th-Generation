package com.jakurudev.pokedex4gen.presentation.pokemon_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jakurudev.pokedex4gen.domain.model.ApiResponse
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.use_case.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases
) : ViewModel() {
    private val _state = MutableStateFlow(PokemonState())
    val state = _state.asStateFlow()

    fun onEvent(event: PokemonEvent) {
        when (event) {
            is PokemonEvent.GetPokemon -> {
                if (state.value.pokemon != null)
                    return

                viewModelScope.launch {
                    val pokemon = pokemonUseCases.getOnePokemonUseCase(id = event.id)
                    if (pokemon is ApiResponse.Success<Pokemon>) {
                        _state.update {
                            it.copy(
                                pokemon = pokemon.data
                            )
                        }
                    }
                }
            }
        }
    }
}