package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.jetpackcompose.effectHandler.withoutEffect.RenderListRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel =  ToDoViewModel()
//        Log.d("MainActivity","onCreate: ${viewModel.hashCode()}")
        enableEdgeToEdge()
        setContent {
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

