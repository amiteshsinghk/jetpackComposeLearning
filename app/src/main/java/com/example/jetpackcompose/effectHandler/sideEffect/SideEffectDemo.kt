package com.example.jetpackcompose.effectHandler.sideEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect

@Composable
fun SideEffectDemo(nonComposeCounter: Int){
    SideEffect {
        println("Called after every successful composition")
    }
}