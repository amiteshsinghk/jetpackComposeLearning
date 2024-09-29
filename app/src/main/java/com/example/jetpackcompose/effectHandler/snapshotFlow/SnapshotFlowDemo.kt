package com.example.jetpackcompose.effectHandler.snapshotFlow

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow

/*
@Composable
fun SnapshotFlowDemo(){
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = scaffoldState) {
        snapshotFlow { scaffoldState.snackbarHostState }
            .mapNotNull { it.currentSnackbarData?.message }
            .distinctUntilChnaged()
            .collect{message ->
                println("A snackbar with message $message was shown")

            }
    }
}*/
