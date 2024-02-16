package com.jakurudev.pokedex4gen.presentation.pokemon_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.jakurudev.pokedex4gen.domain.model.Ability
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.model.Sprites
import com.jakurudev.pokedex4gen.domain.model.Stat
import com.jakurudev.pokedex4gen.presentation.pokemon_screen.components.InfoTabContent
import com.jakurudev.pokedex4gen.ui.theme.ChartColor
import com.jakurudev.pokedex4gen.ui.theme.Pokedex4GenTheme
import com.jakurudev.pokedex4gen.ui.theme.TabColor
import com.jakurudev.pokedex4gen.ui.theme.TopBar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen(
    onEvent: (PokemonEvent) -> Unit,
    navController: NavHostController,
    state: State<PokemonState>
) {
    if (state.value.pokemon == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = state.value.pokemon!!.name,
                        fontSize = 24.sp,
                        color = Color.White
                    )

                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = TopBar),
                navigationIcon = {IconButton(onClick = {
                    navController.popBackStack()
                }, colors = IconButtonDefaults.iconButtonColors(contentColor = Color.White)) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = "Back" )
                }}
            )
        }) {
            content(it, state.value.pokemon!!)
        }
    }
}

@Composable
private fun content(
    it: PaddingValues,
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Info", "Stats", "Sprites")
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(it), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        AsyncImage(
            model = pokemon.sprites.frontDefault,
            contentDescription = null,
            modifier = Modifier.size(200.dp),
            contentScale = ContentScale.Crop
        )

        TabRow(
            selectedTabIndex = selectedTabIndex,
            contentColor = TabColor,
            indicator = { tabPositions ->
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

        when (selectedTabIndex) {
            0 -> InfoTabContent(pokemon = pokemon)
            1 -> StatsTabContent(pokemon = pokemon)
            2 -> SpritesTabContent(pokemon = pokemon)
        }
    }
}


@Composable
fun StatsTabContent(pokemon: Pokemon) {
    BarGraph(stats = pokemon.stats)
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
                Text(text = type.toString(), fontSize = 22.sp)
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
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    }
}

@Composable
fun BarGraph(stats: List<Stat>) {
    val maxValue = stats.maxOfOrNull { it.baseStat } ?: 1
    Column(modifier = Modifier.padding(16.dp)) {
        stats.forEach { stat ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text(
                    text = stat.name,
                    modifier = Modifier.width(120.dp)
                )
                Canvas(modifier = Modifier
                    .height(10.dp)
                    .weight(1f)
                    .padding(horizontal = 8.dp)) {
                    val barWidth = size.width * (stat.baseStat.toFloat() / maxValue)
                    drawLine(
                        color = ChartColor,
                        start = Offset(0f, size.height),
                        end = Offset(barWidth, size.height),
                        strokeWidth = size.height
                    )
                }
                Text(text = "${stat.baseStat}", modifier = Modifier.padding(start = 8.dp))
            }
        }
    }
}
