package com.example.jetpackcompose.effectHandler.produceState

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.delay

/*

Purpose: Used for creating a state that is driven by external sources, such as asynchronous data loading.
When to Use: When you need to load data asynchronously within the composable and return it as state.
*/

@Composable
fun ProduceStateDemo(modifier: Modifier = Modifier) {
    val counter by produceState(0) {
        while(true) {
            delay(1000L)
            value += 1
        }
    }
    Text(
        text = counter.toString(),
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize()
    )
}

@Preview(
    showBackground = true
)
@Composable
private fun ProduceStateDemoPreview() {
    ProduceStateDemo()
}