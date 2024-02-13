package com.jakurudev.pokedex4gen.presentation.main_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.jakurudev.pokedex4gen.domain.use_case.PokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pokemonUseCases: PokemonUseCases
) : ViewModel() {
    val pokemonFlow = pokemonUseCases.getPokemons().cachedIn(viewModelScope)
}