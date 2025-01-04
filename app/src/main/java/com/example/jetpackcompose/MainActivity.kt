package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import com.example.jetpackcompose.effectHandler.withoutEffect.RenderListRoot
import com.example.jetpackcompose.masterclass.performance.DeferredStateReads
import com.example.jetpackcompose.masterclass.performance.ImageLoading
import com.example.jetpackcompose.masterclass.performance.KeysCustomLayout
import com.example.jetpackcompose.masterclass.performance.LazyListPerformance
import com.example.jetpackcompose.masterclass.performance.MovableContent
import com.example.jetpackcompose.masterclass.performance.MyScreen
import com.example.jetpackcompose.masterclass.performance.main_safety.BitmapCompressor
import com.example.jetpackcompose.masterclass.performance.main_safety.PhotoPickerScreen

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalComposeUiApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel =  ToDoViewModel()
//        Log.d("MainActivity","onCreate: ${viewModel.hashCode()}")
        enableEdgeToEdge()
        setContent {

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics {//baseline
                            testTagsAsResourceId = true
                        },
                ) { innerPadding ->
                    LazyListPerformance(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    )
                }


//            DeferredStateReads(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .safeDrawingPadding()
//            )

//            MovableContent(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .safeDrawingPadding()
//            )

//            KeysCustomLayout(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .safeDrawingPadding()
//            )

//            PhotoPickerScreen(
//                compressor = remember {
//                    BitmapCompressor(applicationContext)
//                },
//                modifier = Modifier
//                    .fillMaxSize()
//                    .safeDrawingPadding()
//            )
//            RenderListRoot()
//                    LazyMindMapRoot(modifier = Modifier)

//            ToDoScreenRoot()
//            val currentPage = remember {
//                mutableIntStateOf(0)
//            }
//            Column(modifier = Modifier
//                .fillMaxSize()
//                .safeDrawingPadding()) {
//                SubComposePageRow(
//                    page = currentPage.value,
//                    modifier = Modifier
//                ) {
//                    for (i in 0..1000) {
//                        Box(
//                            modifier = Modifier
//                                .width(Random.nextInt(300).dp)
//                                .height(100.dp)
//                                .background(Color(Random.nextInt()))
//                        )
//                    }
//                }
//                Button(
//                    onClick = { currentPage.value++ }
//                ) {
//                    Text(text = "Next")
//                }
//            }
//

//
//        var mindMapItems = remember {
//            listOf(
//                MindMapItem(
//                    title = "HelloWorld 1",
//                    percentageOffset = Offset(
//                        x = 0f,
//                        y = 0f
//                    )
//                ),
//                MindMapItem(
//                    title = "HelloWorld 2",
//                    percentageOffset = Offset(
//                        x = 1f,
//                        y = -0.5f
//                    )
//                ),
//                MindMapItem(
//                    title = "HelloWorld 3",
//                    percentageOffset = Offset(
//                        x = 0.3f,
//                        y = -0.75f
//                    )
//                ),
//                MindMapItem(
//                    title = "HelloWorld 4",
//                    percentageOffset = Offset(
//                        x = .5f,
//                        y = .05f
//                    )
//                ),
//                MindMapItem(
//                    title = "HelloWorld 5",
//                    percentageOffset = Offset(
//                        x = .25f,
//                        y = .25f
//                    )
//                )
//
//
//            )
//        }
//            var mindMapOffSet by remember {
//                mutableStateOf(IntOffset.Zero)
//            }
//            LazyMindMap(
//                items = mindMapItems,
//                mindMapOffSet = mindMapOffSet,
//                onDrag = {delta ->
//                    mindMapOffSet += delta
//                },
//
//                modifier = Modifier
//                    .fillMaxSize()
//                    .safeDrawingPadding()
//            )

        }
    }
}

