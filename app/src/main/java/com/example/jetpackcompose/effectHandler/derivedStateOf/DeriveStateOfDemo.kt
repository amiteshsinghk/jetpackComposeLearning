package com.example.jetpackcompose.effectHandler.derivedStateOf

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.derivedStateOf

/*
* Side Effect: the code that will run outside the scope of any composable function is called Side Effects.
* */
@Composable
fun DerivedStateOfDemo() {
    var counter by remember { mutableStateOf(0) }
    val counterText by remember { derivedStateOf { "The counter is $counter" } }
    Button(onClick ={ counter++ }) {
        Text(text = counterText)
    }
}