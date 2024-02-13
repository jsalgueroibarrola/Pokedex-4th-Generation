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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.presentation.main_screen.components.PokemonItem
import com.jakurudev.pokedex4gen.ui.theme.Background
import com.jakurudev.pokedex4gen.ui.theme.BorderColorImage

@Composable
fun MainScreen(lazyPokemonItems: LazyPagingItems<Pokemon>) {
    Content(lazyPokemonItems)
}

@Composable
private fun Content(lazyPokemonItems: LazyPagingItems<Pokemon>) {
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
        modifier = Modifier
            .fillMaxSize()
            .background(color = Background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (lazyPokemonItems.loadState.refresh is LoadState.Loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            SelectedPokemonComponent(lazyPokemonItems)
            Spacer(modifier = Modifier.padding(bottom = 4.dp))
            PokemonListComponent(
                lazyPokemonItems = lazyPokemonItems, modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun PokemonListComponent(lazyPokemonItems: LazyPagingItems<Pokemon>, modifier: Modifier) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
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
                    PokemonItem(imageURL = item.sprites.frontDefault, name = item.name)
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
}

@Composable
private fun SelectedPokemonComponent(lazyPokemonItems: LazyPagingItems<Pokemon>) {
    Box(
        modifier = Modifier
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
        if (lazyPokemonItems.loadState.refresh !is LoadState.Loading) {
            AsyncImage(
                model = lazyPokemonItems[0]?.sprites?.frontDefault,
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp),
                contentScale = ContentScale.Crop
            )
        }

    }
}