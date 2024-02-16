package com.jakurudev.pokedex4gen.presentation.pokemon_screen.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jakurudev.pokedex4gen.domain.model.Pokemon
import com.jakurudev.pokedex4gen.domain.model.heightToFeet
import com.jakurudev.pokedex4gen.domain.model.heightToMeter
import com.jakurudev.pokedex4gen.domain.model.idToString
import com.jakurudev.pokedex4gen.domain.model.weightToKg
import com.jakurudev.pokedex4gen.domain.model.weightToOunces
import com.jakurudev.pokedex4gen.domain.model.weightToPounds
import com.jakurudev.pokedex4gen.domain.util.getChipBackgroundColor
import com.jakurudev.pokedex4gen.domain.util.getChipTextColor

@Composable
fun InfoTabContent(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Text(
        text = pokemon.idToString(),
        fontSize = 18.sp,
        modifier = Modifier.padding(top = 16.dp, bottom = 16.dp)
    )
    TypeChips(pokemon = pokemon)
    Text(
        text = "Ability",
        fontSize = 18.sp,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    AbilitiesText(pokemon = pokemon)
    Text(
        text = "Height",
        fontSize = 18.sp,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
    )
    heightsText(pokemon)
    Text(
        text = "Weight",
        fontSize = 18.sp,
        color = Color.Gray,
        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
    )
    WeightsText(pokemon)
}

@Composable
private fun WeightsText(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(bottom = 16.dp)) {
        Text(
            text = "%.2f".format(pokemon.weightToKg()) + " kg",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
        )
        Divider(
            modifier = Modifier
                .height(25.dp)
                .width(1.dp),
            color = Color.DarkGray
        )
        Text(
            text = "%.2f".format(pokemon.weightToOunces()) + " oz",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
        )
        Divider(
            modifier = Modifier
                .height(25.dp)
                .width(1.dp),
            color = Color.DarkGray
        )
        Text(
            text = "%.2f".format(pokemon.weightToPounds()) + " lbs",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
        )
    }
}

@Composable
private fun heightsText(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(bottom = 16.dp)) {
        Text(
            text = "%.2f".format(pokemon.heightToFeet()) + " ft",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
        )
        Divider(
            modifier = Modifier
                .height(25.dp)
                .width(1.dp),
            color = Color.DarkGray
        )
        Text(
            text = "%.2f".format(pokemon.heightToMeter()) + " m",
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .weight(1f)
        )
    }
}

@Composable
private fun AbilitiesText(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in pokemon.abilities) {
            Text(
                i.name,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp)
                    .weight(1f)
            )
            if (pokemon.abilities.last() != i) {
                Divider(
                    modifier = Modifier
                        .height(25.dp)
                        .width(1.dp),
                    color = Color.DarkGray
                )
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun TypeChips(pokemon: Pokemon, modifier: Modifier = Modifier) {
    Row(modifier = modifier.padding(bottom = 8.dp)) {
        for (i in pokemon.types) {
            SuggestionChip(
                onClick = { },
                label = {
                    Text(
                        i,
                        textAlign = TextAlign.Center,
                        color = getChipTextColor(i),
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                colors = SuggestionChipDefaults.suggestionChipColors(
                    disabledContainerColor = getChipBackgroundColor(i)
                ),
                enabled = false,
                border = SuggestionChipDefaults.suggestionChipBorder(borderWidth = 0.dp),
                modifier = Modifier
                    .width(100.dp)
                    .weight(1f)
                    .padding(start = 24.dp, end = 24.dp),
            )
        }
    }
}