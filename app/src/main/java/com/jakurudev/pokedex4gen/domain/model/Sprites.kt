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
    fun getSprites(type: Type): List<String> {
        val list = mutableListOf<String>()
        if(type == Type.SHINY_MALE){
            list.add(frontShiny)
            list.add(backShiny)
        }
        else if (type == Type.SHINY_FEMALE && !frontFemale.isNullOrBlank()){
            list.add(frontShinyFemale!!)
            list.add(backShinyFemale!!)
        }
        else if (type == Type.FEMALE && !frontShinyFemale.isNullOrBlank()){
            list.add(frontFemale!!)
            list.add(backFemale!!)
        }
        else if (type == Type.MALE){
            list.add(frontDefault)
            list.add(backDefault)
        }

        return list
    }

    enum class Type {
        SHINY_FEMALE, SHINY_MALE, MALE, FEMALE
    }
}
