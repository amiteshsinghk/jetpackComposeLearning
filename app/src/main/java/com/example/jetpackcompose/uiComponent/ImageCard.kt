package com.example.jetpackcompose.uiComponent

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GetImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    height: Int,
    width: Int,
    cornerRadius: Int,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .height(height.dp)
            .width(width.dp),
        shape = RoundedCornerShape(cornerRadius)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                modifier = Modifier
                    .height(height.dp)
                    .width(width = width.dp),
                painter = painter,
                contentDescription = contentDescription
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ), startY = 300f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = title,
                    fontSize = 16.sp,
                    style = TextStyle(color = Color.White, textAlign = TextAlign.Start),
                    modifier = Modifier.padding(16.dp)
                )
            }

        }
    }
}