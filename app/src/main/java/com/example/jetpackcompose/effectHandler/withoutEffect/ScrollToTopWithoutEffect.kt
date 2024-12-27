package com.example.jetpackcompose.effectHandler.withoutEffect


import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/*
Here we are not using any side effect inside our composable function. All the computation is in viewModel and observing the state.
*/

class ScrollViewModel: ViewModel(){
    var lazyListState = LazyListState()
    var snackbarHostState = SnackbarHostState()
    var canScrollToTop = snapshotFlow { lazyListState.firstVisibleItemIndex }
        .map { it>=10 }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false
        )
    val list = (0..100).map {
        "Item $it"
    }
    var isLastItem = snapshotFlow { lazyListState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
        .map { it == list.size-1  }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(),
            false)
    init {
        viewModelScope.launch {
            isLastItem.collectLatest {
            if (it) showSnackbar(this,"Scrolled to bottom")
            }
        }
    }


    fun showSnackbar(scope: CoroutineScope, message: String?){
        scope.launch {
            snackbarHostState.showSnackbar(message ?: "Scrolled to top")
        }
    }

    
    fun scrollToTop(uiScope: CoroutineScope){
        viewModelScope.launch {
            withContext(uiScope.coroutineContext){ lazyListState.animateScrollToItem(0) }
        }
    }

}
@Composable
fun RenderListRoot(){
    val viewModel: ScrollViewModel = remember { ScrollViewModel() }
    val canScrollToTop by viewModel.canScrollToTop.collectAsStateWithLifecycle()
    val scope = rememberCoroutineScope()
    RenderList(list = viewModel.list,
        state = viewModel.lazyListState,
      canScrollToTop =canScrollToTop,
        hostState = viewModel.snackbarHostState,
        scrollToTop = {
        viewModel.scrollToTop(scope)
        viewModel.showSnackbar(scope, null)
    },
        modifier = Modifier)
}

@Composable
fun RenderList(
    list: List<String>,
    state: LazyListState,
    canScrollToTop: Boolean,
    hostState: SnackbarHostState,
    scrollToTop:()->Unit,
    modifier: Modifier
){

    Scaffold (
        snackbarHost = {
                SnackbarHost(
                    hostState = hostState
                )
        },
        floatingActionButton = {
            Log.d("ScrollToTopWithoutEffect","canScrollToTop $canScrollToTop")
            if (canScrollToTop) {
                Log.d("ScrollToTopWithoutEffect","canScrollToTop Inside $canScrollToTop")
                FloatingActionButton(
                    onClick = {
                        scrollToTop()
                    }
                ) {
                   Icon(
                       imageVector = Icons.Default.KeyboardArrowUp,
                       contentDescription = null
                   )
                }
            }
        }
    ){innerPadding->
        LazyColumn(
            state = state,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(list) {
                Text(text = it,
                    modifier = Modifier
                        .padding(16.dp))
            }

        }
    }

}

val list = (0..20).map {
    "Item $it"
}

@Preview(
    showBackground = true
)
@Composable
private fun RenderListPreview() {
    RenderList(
        list,
        canScrollToTop = false,
        state = LazyListState(),
        modifier = Modifier,
        hostState = SnackbarHostState(),
        scrollToTop = {}
    )
}