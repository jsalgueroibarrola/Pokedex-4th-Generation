package com.jakurudev.pokedex4gen.presentation.main_screen.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.jakurudev.pokedex4gen.presentation.main_screen.MainEvent
import com.jakurudev.pokedex4gen.presentation.main_screen.MainState
import com.jakurudev.pokedex4gen.ui.theme.BackgroundColorPokedex
import com.jakurudev.pokedex4gen.ui.theme.SelectedBackgroundColorPokedex
import com.jakurudev.pokedex4gen.ui.theme.TextColorPokedex

@Composable
fun PokemonItem(
    imageURL: String,
    name: String,
    isDisplay: Boolean,
    navigationPokemon: () -> Unit,
    displayPokemon: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        contentAlignment = Alignment.Center
    ) {
        BackgroundList(isDisplay = isDisplay, modifier = Modifier.height(60.dp))
        Row(
            modifier = Modifier.fillMaxWidth().clickable { navigationPokemon() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            PokeBall(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .clickable { displayPokemon() }
            )
            AsyncImage(
                model = imageURL,
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(60.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "N. 001",
                fontSize = 22.sp,
                color = TextColorPokedex,
                modifier = Modifier.padding(start = 24.dp)
            )
            Text(
                text = name,
                fontSize = 22.sp,
                color = TextColorPokedex,
                modifier = Modifier
                    .padding(start = 24.dp, end = 8.dp)
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun BackgroundList(isDisplay: Boolean, modifier: Modifier) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isDisplay) SelectedBackgroundColorPokedex else BackgroundColorPokedex,
        label = ""
    )
    Canvas(
        modifier = modifier
            .fillMaxWidth()
            .height(100.dp)
    ) {
        val lefRadius = size.height * 0.5f
        drawCustomRoundRect(
            color = backgroundColor,
            size = Size(width = size.width, height = size.height),
            topLeftRadius = CornerRadius(x = lefRadius, y = lefRadius),
            topRightRadius = CornerRadius(x = 50f, y = 50f),
            bottomRightRadius = CornerRadius(
                x = 50f,
                y = 50f
            ),
            bottomLeftRadius = CornerRadius(
                x = lefRadius,
                y = lefRadius
            )
        )
    }
}

fun DrawScope.drawCustomRoundRect(
    color: Color,
    size: Size,
    topLeftRadius: CornerRadius,
    topRightRadius: CornerRadius,
    bottomRightRadius: CornerRadius,
    bottomLeftRadius: CornerRadius
) {
    val path = Path().apply {
        reset()

        moveTo(0f, topLeftRadius.y)
        quadraticBezierTo(0f, 0f, topLeftRadius.x, 0f)

        lineTo(size.width - topRightRadius.x, 0f)

        quadraticBezierTo(size.width, 0f, size.width, topRightRadius.y)

        lineTo(size.width, size.height - bottomRightRadius.y)

        quadraticBezierTo(size.width, size.height, size.width - bottomRightRadius.x, size.height)

        lineTo(bottomLeftRadius.x, size.height)

        quadraticBezierTo(0f, size.height, 0f, size.height - bottomLeftRadius.y)

        close()
    }
    drawPath(path, color)
}