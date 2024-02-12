package com.jakurudev.pokedex4gen.domain.model

data class Sprites(
    val backDefault: String,
    val backFemale: String?,
    val backShiny: String,
    val backShinyFemale: String?,
    val frontDefault: String,
    val frontFemale: String?,
    val frontShiny: String,
    val frontShinyFemale: String?,
) {
    fun getSprite(gender: Gender, view: View, isShiny: Boolean): String? {
        return when (view) {
            View.FRONT -> when (gender) {
                Gender.MALE -> if (isShiny) frontShiny else frontDefault
                Gender.FEMALE -> if (isShiny) frontShinyFemale else frontFemale ?: frontDefault
            }

            View.BACK -> when (gender) {
                Gender.MALE -> if (isShiny) backShiny else backDefault
                Gender.FEMALE -> if (isShiny) backShinyFemale else backFemale ?: backDefault
            }
        }
    }

    enum class Gender {
        MALE, FEMALE
    }

    enum class View {
        FRONT, BACK
    }
}
