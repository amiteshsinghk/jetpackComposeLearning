package com.example.jetpackcompose.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import kotlin.random.Random

@Composable
fun ChangeBoxColor(modifier: Modifier, colorState: (Color) -> Unit) {
    // Here when the component recompose, color =  mutableStateOf(Color.Yellow) will be reset too.
    // So to avoid that we can use remember. Remember will remember the value until the component
    // recomposes.
    // val color = remember {
    // mutableStateOf(Color.Yellow)
    // }
    Box(modifier = modifier
        .background(Color.Green)
        .clickable {
            colorState(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(), 1f
                )
            )
        })
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    Column {
        val color = remember {
            mutableStateOf(Color.Yellow)
        }
        ChangeBoxColor(
            modifier = Modifier
                .weight(1f)
                .fillMaxSize(

                )
        ) {
            color.value = it
        }
        Box(
            modifier = Modifier
                .background(color.value)
                .weight(1f)
                .fillMaxSize()
        )
    }

}