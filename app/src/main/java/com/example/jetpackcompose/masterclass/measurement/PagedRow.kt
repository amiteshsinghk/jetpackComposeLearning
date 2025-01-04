package com.example.jetpackcompose.masterclass.measurement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.SubcomposeLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMaxOfOrNull
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun PagedRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Layout(
        content = content,
        modifier = modifier
    ) { measurables, constraints ->
        val placeable = measurables.map {
            it.measure(constraints)
        }

        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0

        placeable.fastForEach { placeable ->
            if(currentPageWidth + placeable.width > constraints.maxWidth) {
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }

        if(currentPage.isNotEmpty()) {
            pages.add(currentPage)
        }

        val pageItems = pages.getOrNull(page) ?: emptyList()
        val maxHeight = pageItems.fastMaxOfOrNull { it.height } ?: 0

        layout(constraints.maxWidth, maxHeight) {
            var xOffset = 0
            pageItems.fastForEach { placeable ->
                placeable.place(xOffset, 0)
                xOffset += placeable.width
            }
        }
    }
}

@Composable
fun SubcomposePagedRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    SubcomposeLayout(
        modifier = modifier
    ) { constraints ->
        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0

        val measurables = subcompose("content", content)

        for(measurable in measurables) {
            val placeable = measurable.measure(constraints)

            if(currentPageWidth + placeable.width > constraints.maxWidth) {
                if(pages.size == page) {
                    break
                }
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width
        }

        if(currentPage.isNotEmpty()) {
            pages.add(currentPage)
        }

        val pageItems = pages.getOrNull(page) ?: emptyList()
        val maxHeight = pageItems.fastMaxOfOrNull { it.height } ?: 0

        layout(constraints.maxWidth, maxHeight) {
            var xOffset = 0
            pageItems.fastForEach { placeable ->
                placeable.place(xOffset, 0)
                xOffset += placeable.width
            }
        }
    }
}

@Preview
@Composable
private fun PagedRowPreview() {
    JetpackComposeTheme {
        SubcomposePagedRow(
            page = 0
        ) {
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Red)
            )
            Box(
                modifier = Modifier
                    .width(50.dp)
                    .height(150.dp)
                    .background(Color.Yellow)
            )
            Box(
                modifier = Modifier
                    .width(75.dp)
                    .height(100.dp)
                    .background(Color.Green)
            )
            Box(
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .background(Color.Blue)
            )
        }
    }
}