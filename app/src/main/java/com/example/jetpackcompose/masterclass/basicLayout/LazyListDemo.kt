package com.example.jetpackcompose.masterclass.basicLayout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme
import kotlin.random.Random

@Composable
fun LazyListDemo(modifier: Modifier = Modifier) {
    LazyRow(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(100) { i ->
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(Color(Random.nextInt()))
            )
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun FlexLayoutDemoPreview() {
    JetpackComposeTheme {
        LazyListDemo()
    }
}