package com.jakurudev.pokedex4gen.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jakurudev.pokedex4gen.domain.use_case.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases
) : ViewModel() {
    val pokemonFlow = pokemonUseCases.getPokemons().cachedIn(viewModelScope)
    private val _state = MutableStateFlow(MainState())
    val state = _state.asStateFlow()

    fun onEvent(event: MainEvent){
        when(event) {
            is MainEvent.DisplayPokemon -> {
                _state.update {
                    it.copy(
                        displayedPokemon = event.pokemon.apply {
                            isDisplay = true
                            it.displayedPokemon?.isDisplay = false
                        }
                    )
                }
            }
        }
    }

}