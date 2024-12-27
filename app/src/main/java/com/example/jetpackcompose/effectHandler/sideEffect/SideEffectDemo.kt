package com.example.jetpackcompose.effectHandler.sideEffect

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

@Composable
fun SideEffectHandlerDemo(modifier: Modifier = Modifier) {
    SideEffect {
        println("Called after successful recomposition")
    }
}

@Composable
fun SideEffectDemo(modifier: Modifier = Modifier) {
    var counter by remember {
        mutableIntStateOf(0)
    }
    Button(
        onClick = {
            counter++
        }
    ) {
        counter++
        Text("Counter: $counter")
    }
}

@Preview
@Composable
private fun SideEffectDemoPreview() {
    JetpackComposeTheme {
        SideEffectDemo()
    }
}