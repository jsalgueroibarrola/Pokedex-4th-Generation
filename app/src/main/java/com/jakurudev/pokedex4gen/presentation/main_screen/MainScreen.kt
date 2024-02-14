package com.jakurudev.pokedex4gen.presentation.main_screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.presentation.main_screen.components.PokemonItem
import com.jakurudev.pokedex4gen.ui.theme.Background
import com.jakurudev.pokedex4gen.ui.theme.BorderColorImage
import com.jakurudev.pokedex4gen.ui.theme.TopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    lazyPokemonItems: LazyPagingItems<Pokemon>,
    onEvent: (MainEvent) -> Unit,
    state: State<MainState>,
    navController: NavHostController
) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Pokédex de Sinnoh",
                    fontSize = 24.sp,
                    color = Color.White
                )

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = TopBar)
        )
    }) {
        Content(
            lazyPokemonItems = lazyPokemonItems,
            state = state,
            onEvent = onEvent,
            modifier = Modifier.padding(it)
        )
    }

}

@Composable
private fun Content(
    lazyPokemonItems: LazyPagingItems<Pokemon>,
    state: State<MainState>,
    onEvent: (MainEvent) -> Unit,
    modifier: Modifier,
) {
    val context = LocalContext.current

    LaunchedEffect(key1 = lazyPokemonItems.loadState) {
        if (lazyPokemonItems.loadState.refresh is LoadState.Error) {
            Toast.makeText(
                context,
                "Error: " + (lazyPokemonItems.loadState.refresh as LoadState.Error).error.message,
                Toast.LENGTH_LONG
            ).show()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (lazyPokemonItems.loadState.refresh is LoadState.Loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            SelectedPokemonComponent(
                pokemon = state.value.displayedPokemon,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            PokemonListComponent(
                lazyPokemonItems = lazyPokemonItems,
                state = state,
                onEvent = onEvent,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun PokemonListComponent(
    lazyPokemonItems: LazyPagingItems<Pokemon>,
    state: State<MainState>,
    onEvent: (MainEvent) -> Unit,
    modifier: Modifier,
) {
    val listState = rememberLazyListState()

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp),
        ) {
            items(
                count = lazyPokemonItems.itemCount,
                key = lazyPokemonItems.itemKey { it.id },
            ) { index ->
                val item = lazyPokemonItems[index]
                item?.let {
                    PokemonItem(
                        imageURL = item.sprites.frontDefault,
                        name = item.name,
                        isDisplay = item.isDisplay,
                        displayPokemon = { onEvent(MainEvent.DisplayPokemon(pokemon = item)) },
                        navigationPokemon = {  }
                    )
                }
            }
            item {
                if (lazyPokemonItems.loadState.append is LoadState.Loading) {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }

    LaunchedEffect(listState) {
        snapshotFlow {
            Pair(
                listState.firstVisibleItemIndex,
                listState.firstVisibleItemScrollOffset
            )
        }
            .collect { (index, offset) ->
                if (offset > 90) {
                    if (state.value.displayedPokemon?.name != lazyPokemonItems[index + 1]?.name) {
                        lazyPokemonItems[index + 1]?.let { onEvent(MainEvent.DisplayPokemon(it)) }
                    }
                } else {
                    lazyPokemonItems[index]?.let { onEvent(MainEvent.DisplayPokemon(it)) }
                }
            }
    }
}

@Composable
private fun SelectedPokemonComponent(
    pokemon: Pokemon?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(color = Color.White, shape = RoundedCornerShape(size = 16.dp))
            .size(width = 200.dp, height = 200.dp)
            .border(
                border = BorderStroke(
                    width = 5.dp,
                    color = BorderColorImage
                ),
                shape = RoundedCornerShape(size = 16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        if (pokemon != null) {
            AsyncImage(
                model = pokemon.sprites.frontDefault,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )
        }
    }
}