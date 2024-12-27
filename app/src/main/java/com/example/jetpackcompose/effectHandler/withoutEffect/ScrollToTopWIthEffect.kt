package com.example.jetpackcompose.effectHandler.withoutEffect

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

/*
*Show a floating button when the 10th item in the list becomes invisible.
* On clicking the button, scroll the list to the top.
*
* As per the google docs we should
* */
@Composable
fun ScrollToTopWithEffect() {
    val lazyListState = rememberLazyListState()
    val scrollToTop by remember {
        derivedStateOf {
            lazyListState.firstVisibleItemIndex >= 10
        }
    }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LazyColumn(state = lazyListState, modifier = Modifier) {
            items(1000) {
                Text(text = "Item $it",modifier = Modifier.padding(16.dp))
            }
        }
        if (scrollToTop) {
            FloatingActionButton(
                onClick = {
                    scope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }
            ) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = null
                )
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
private fun ScrollToTopWithEffectPreview() {
    ScrollToTopWithEffect()
}