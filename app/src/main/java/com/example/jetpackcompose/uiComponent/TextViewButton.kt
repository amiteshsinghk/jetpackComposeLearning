package com.example.jetpackcompose.uiComponent

import android.widget.Button
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch


@Preview(showBackground = true)
@Composable
fun GreetingPreview4() {
    val snackbarHostState = remember { SnackbarHostState() }
    var textFieldState by remember {  mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Scaffold(modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(15.dp).fillMaxSize()
        ) {
            textFieldState= getTextField()
            Spacer(modifier = Modifier.height(15.dp))
            MyButtonWithCallback {
                scope.launch {
                    snackbarHostState.showSnackbar("Hello $textFieldState")
                }
            }
        }
    }
}

@Composable
fun MyButtonWithCallback(onButtonClicked: () -> Unit) {
    Button(onClick = { onButtonClicked() }) {
        Text("Submit")
    }
}

@Composable
fun getTextField(): String {
    var textFieldStates by remember {  mutableStateOf("") }
    OutlinedTextField(
        value = textFieldStates,
        label = {
            Text(text = "Enter your name")
        },
        onValueChange = {
            textFieldStates = it
        }, singleLine = true,
        modifier = Modifier.fillMaxWidth()

    )
    return textFieldStates
}