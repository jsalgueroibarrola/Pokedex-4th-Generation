package com.jakurudev.pokedex4gen.presentation.pokemon_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jakurudev.pokedex4gen.domain.model.Ability
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.model.Sprites
import com.jakurudev.pokedex4gen.domain.model.Stat
import com.jakurudev.pokedex4gen.domain.model.heightToFeet
import com.jakurudev.pokedex4gen.domain.model.heightToMeter
import com.jakurudev.pokedex4gen.domain.model.weightToKg
import com.jakurudev.pokedex4gen.domain.model.weightToOunces
import com.jakurudev.pokedex4gen.domain.model.weightToPounds
import com.jakurudev.pokedex4gen.domain.util.getChipBackgroundColor
import com.jakurudev.pokedex4gen.domain.util.getChipTextColor
import com.jakurudev.pokedex4gen.presentation.pokemon_screen.components.InfoTabContent
import com.jakurudev.pokedex4gen.ui.theme.Pokedex4GenTheme
import com.jakurudev.pokedex4gen.ui.theme.TabColor
import com.jakurudev.pokedex4gen.ui.theme.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen() {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Info", "Stats", "Sprites")

    val pokemon = getExamplePokemon()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = pokemon.name,
                    fontSize = 24.sp,
                    color = Color.White
                )

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = TopBar)
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it), horizontalAlignment = Alignment.CenterHorizontally
        ) {

            AsyncImage(
                model = pokemon.sprites.frontDefault,
                contentDescription = null,
                modifier = Modifier.size(200.dp),
                contentScale = ContentScale.Crop
            )

            TabRow(selectedTabIndex = selectedTabIndex, contentColor = TabColor, indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]),
                    color = TabColor,
                )
            }) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        text = { Text(title) },
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        selectedContentColor = TabColor,
                        unselectedContentColor = Color.Gray
                    )
                }
            }

            // Simple pager
            when (selectedTabIndex) {
                0 -> InfoTabContent(pokemon = pokemon)
                1 -> StatsTabContent(pokemon = pokemon)
                2 -> SpritesTabContent(pokemon = pokemon)
            }
        }
    }
}


@Composable
fun StatsTabContent(pokemon: Pokemon) {
    Text("Stats Content", modifier = Modifier.padding(16.dp))
}

@Composable
fun SpritesTabContent(pokemon: Pokemon, modifier: Modifier = Modifier) {
    val sprites = mutableMapOf<Sprites.Type, List<String>>()
    sprites[Sprites.Type.MALE] = pokemon.sprites.getSprites(Sprites.Type.MALE)
    sprites[Sprites.Type.FEMALE] = pokemon.sprites.getSprites(Sprites.Type.FEMALE)
    sprites[Sprites.Type.SHINY_MALE] = pokemon.sprites.getSprites(Sprites.Type.SHINY_MALE)
    sprites[Sprites.Type.SHINY_FEMALE] = pokemon.sprites.getSprites(Sprites.Type.SHINY_FEMALE)

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentPadding = PaddingValues(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(sprites.keys.toList()) { type: Sprites.Type ->
            val list: List<String>? = sprites[type]
            if (!list.isNullOrEmpty()) {
                LazyRow {
                    items(list) {
                        AsyncImage(
                            model = it,
                            contentDescription = null,
                            modifier = Modifier.size(180.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                Text(text = type.toString(), fontSize = 22.sp)
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTabsExample() {
    Pokedex4GenTheme {

        PokemonScreen()
    }
}


private fun getExamplePokemon() = Pokemon(
    id = 6,
    abilities = listOf(Ability(name = "blaze", false), Ability(name = "solar power", true)),
    name = "Charizard",
    sprites = Sprites(
        backDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/6.png",
        backShinyFemale = null,
        backShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/back/shiny/6.png",
        backFemale = null,
        frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png",
        frontFemale = null,
        frontShiny = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/shiny/6.png",
        frontShinyFemale = null
    ),
    heightDecimeter = 17,
    weightHectogram = 905,
    types = listOf("ghost", "ice"),
    stats = listOf(
        Stat(78, 0, "hp"),
        Stat(84, 0, "attack"),
        Stat(78, 0, "defense"),
        Stat(109, 0, "special attack"),
        Stat(85, 0, "special-defense"),
        Stat(100, 0, "speed"),
    )

)