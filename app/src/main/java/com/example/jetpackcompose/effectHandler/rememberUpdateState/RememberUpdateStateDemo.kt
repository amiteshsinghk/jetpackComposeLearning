package com.example.jetpackcompose.effectHandler.rememberUpdateState

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import kotlinx.coroutines.delay

@Composable
fun RememberUpdateStateDemo(
    onTimeout: () -> Unit
) {
    val updatedOnTimeout by rememberUpdatedState(newValue = onTimeout)
    LaunchedEffect(true) {
        delay(3000L)
        onTimeout()
    }
}