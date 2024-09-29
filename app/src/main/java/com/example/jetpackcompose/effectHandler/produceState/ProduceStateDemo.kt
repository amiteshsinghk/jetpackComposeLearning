package com.example.jetpackcompose.effectHandler.produceState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import kotlinx.coroutines.delay

/*

Purpose: Used for creating a state that is driven by external sources, such as asynchronous data loading.
When to Use: When you need to load data asynchronously within the composable and return it as state.
*/

@Composable
fun produceStateDemo(countUpto: Int): State<Int> {
    return produceState(initialValue = 0) {
        while (value <countUpto){
            delay(1000L)
            value++
        }
    }
}