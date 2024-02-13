package com.jakurudev.pokedex4gen.data.mapper

import com.jakurudev.pokedex4gen.data.response.AbilityResponse
import com.jakurudev.pokedex4gen.data.response.PokemonAbilityResponse
import com.jakurudev.pokedex4gen.domain.model.Ability


fun PokemonAbilityResponse.toAbility() : Ability {
    return Ability(name = abilityResponse.name, isHidden = isHidden)
}

// fun Ability.toAbilityResponse() : AbilityResponse {}