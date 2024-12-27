package com.example.jetpackcompose.masterclass

import android.util.Log
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
fun PageRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Layout(
        content = content,
        modifier = modifier
    ){
        measurables, constraints ->
        //measurables -> Actual composable placed in the layout
        //constraint -> Refer to the root constraints such as modifier constraints
        val placeables = measurables.map {
            it.measure(constraints)
        }
        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0
        // Placing the items in pages
        placeables.fastForEach {placeable->
            if (currentPageWidth+placeable.width > constraints.maxWidth){
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width

        }
        if (currentPage.isNotEmpty()){
            pages.add(currentPage)
        }
        // Get the current page
        val pageItem = pages.getOrNull(page) ?: emptyList()
       layout(constraints.maxWidth, constraints.maxHeight){
           var xPosition = 0
           pageItem.fastForEach { placeables ->
               placeables.place(xPosition,0)
               xPosition += placeables.width

           }

       }
    }
}

// It will only measure those children which need to draw. It'll not measure the whole composable.
@Composable
fun SubComposePageRow(
    page: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    SubcomposeLayout  (
        modifier = modifier
    ){ constraints ->
        val pages = mutableListOf<List<Placeable>>()
        var currentPage = mutableListOf<Placeable>()
        var currentPageWidth = 0

        val measurables = subcompose("content",content)
        // Placing the items in pages
        var i = 0
        for(measurable in measurables) {
            i++
            val placeable = measurable.measure(constraints)
            if (currentPageWidth+placeable.width > constraints.maxWidth){
                if (pages.size == page){
                    break
                }
                pages.add(currentPage)
                currentPage = mutableListOf()
                currentPageWidth = 0
            }
            currentPage.add(placeable)
            currentPageWidth += placeable.width

        }
        Log.d("SubComposePageRow", "SubComposePageRow: $i")
        if (currentPage.isNotEmpty()){
            pages.add(currentPage)
        }
        // Get the current page
        val pageItem = pages.getOrNull(page) ?: emptyList()
        val maxHeight = pageItem.fastMaxOfOrNull {
            it.height
        } ?:0
        layout(constraints.maxWidth, maxHeight){
            var xPosition = 0
            pageItem.fastForEach { placeables ->
                placeables.place(xPosition,0)
                xPosition += placeables.width

            }

        }
    }
}


@Preview
@Composable
private fun PagedRowPreview(){
    JetpackComposeTheme {
        SubComposePageRow (
            page = 0,
            ){
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