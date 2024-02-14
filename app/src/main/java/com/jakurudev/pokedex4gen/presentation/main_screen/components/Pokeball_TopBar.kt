package com.jakurudev.pokedex4gen.presentation.main_screen.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jakurudev.pokedex4gen.ui.theme.BorderPokeball
import com.jakurudev.pokedex4gen.ui.theme.RedPokeball
import com.jakurudev.pokedex4gen.ui.theme.WhitePokeball

@Composable
fun PokeBallTopBar(modifier: Modifier) {
    Column(modifier = modifier){
        Canvas(
            modifier = Modifier
                .size(100.dp) // Ajusta este tamaño como necesites
                .background(Color.Yellow)
        ) {
            val strokeWidth = 4.dp.toPx() // Grosor del trazo
            val arcRadius = size.minDimension / 4 // Radio del arco, ajusta para cambiar el tamaño del arco
            val arcRect = Rect(
                Offset(size.width / 2 - arcRadius, size.height / 2 - arcRadius),
                Offset(size.width / 2 + arcRadius, size.height / 2 + arcRadius)
            )

            drawPath(
                path = Path().apply {
                    arcTo(arcRect, 0f, 180f, false)
                },
                color = Color.Black,
                style = Stroke(width = strokeWidth)
            )
        }

        Canvas(
            modifier = modifier
                .size(300.dp)
        ) {
            val strokeWidth = size.width * 0.05f
            val diameter = minOf(size.width, size.height) * 0.4f

            drawCircle(
                color = WhitePokeball,
                center = Offset(size.width / 2, size.height / 2 ),
                radius = diameter / 2.5f
            )
        }

        Box(contentAlignment = Alignment.TopCenter) {
            // Primer Canvas para la parte superior de la Pokeball
            Canvas(modifier = Modifier.size(300.dp)) {
                drawArc(
                    color = Color.Red, // Asumiendo RedPokeball es un Color válido
                    startAngle = 0f,
                    sweepAngle = 180f,
                    useCenter = true, // Cambiado a true para llenar el semicírculo
                    size = Size(size.width, size.height / 2), // Ajuste para llenar correctamente la mitad superior
                    topLeft = center.copy(x = 0f, y = 0f) - center.copy(y = size.height / 4) // Ajuste para centrar verticalmente
                )
            }

            // Segundo Canvas para la parte inferior de la Pokeball
            Canvas(modifier = Modifier.size(300.dp)) {
                drawArc(
                    color = Color.White, // Asumiendo WhitePokeball es un Color válido
                    startAngle = 180f,
                    sweepAngle = 180f,
                    useCenter = true, // Cambiado a true para llenar el semicírculo
                    size = Size(size.width, size.height / 2), // Ajuste para llenar correctamente la mitad inferior
                    topLeft = center.copy(x = 0f, y = size.height / 2) - center.copy(y = size.height / 4) // Ajuste para centrar verticalmente
                )
            }
        }
    }

}

@Preview
@Composable
fun PokeBallTopBar() {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)) {
        PokeBallTopBar(modifier = Modifier)
    }
}