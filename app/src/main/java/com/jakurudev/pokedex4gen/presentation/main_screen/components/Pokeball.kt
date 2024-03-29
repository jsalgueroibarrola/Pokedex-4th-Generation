package com.jakurudev.pokedex4gen.presentation.main_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakurudev.pokedex4gen.ui.theme.BorderPokeball
import com.jakurudev.pokedex4gen.ui.theme.RedPokeball
import com.jakurudev.pokedex4gen.ui.theme.WhitePokeball

@Composable
fun PokeBall(modifier: Modifier) {
    Canvas(
        modifier = modifier
            .size(300.dp)
    ) {
        val strokeWidth = size.width * 0.05f
        val diameter = minOf(size.width, size.height) * 0.4f

        drawArc(
            color = RedPokeball,
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width, size.height),
        )

        drawArc(
            color = BorderPokeball,
            startAngle = 0f,
            sweepAngle = -180f,
            useCenter = true,
            size = Size(size.width, size.height),
            style = Stroke(width = strokeWidth)
        )

        drawArc(
            color = WhitePokeball,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width, size.height),
        )

        drawArc(
            color = BorderPokeball,
            startAngle = 0f,
            sweepAngle = 180f,
            useCenter = true,
            size = Size(size.width, size.height),
            style = Stroke(width = strokeWidth)
        )

        drawCircle(
            color = BorderPokeball,
            center = Offset(size.width / 2, size.height / 2),
            radius = diameter / 2
        )

        drawCircle(
            color = WhitePokeball,
            center = Offset(size.width / 2, size.height / 2),
            radius = diameter / 2.5f
        )
    }
}

@Preview
@Composable
fun preview() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        PokeBall(modifier = Modifier)
    }
}