package com.example.jetpackcompose.effectHandler.launchedEffect

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.collect

@Composable
fun LaunchedEffectFlowDemo(
    viewModel: LaunchEffectViewModel
) {
    LaunchedEffect(key1 = true) {
        viewModel.sharedFlow.collect { event ->
            when (event) {
                is LaunchEffectViewModel.ScreenEvents.ShowSnackbar -> {
                }

                is LaunchEffectViewModel.ScreenEvents.Navigate -> {


                }
            }
        }
    }

}