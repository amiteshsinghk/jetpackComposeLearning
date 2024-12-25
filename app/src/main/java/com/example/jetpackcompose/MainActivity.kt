package com.example.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.masterclass.HotelBookingScreen
import com.example.jetpackcompose.masterclass.SubComposePageRow
import com.example.jetpackcompose.masterclass.measurement.LazyMindMap
import com.example.jetpackcompose.masterclass.measurement.MindMapItem
import com.example.jetpackcompose.masterclass.todoScreen.ToDoScreenRoot
import com.example.jetpackcompose.masterclass.todoScreen.ToDoViewModel
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel =  ToDoViewModel()
//        Log.d("MainActivity","onCreate: ${viewModel.hashCode()}")
        enableEdgeToEdge()
        setContent {
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
        var mindMapItems = remember {
            listOf(
                MindMapItem(
                    title = "HelloWorld 1",
                    percentageOffset = Offset(
                        x = 0f,
                        y = 0f
                    )
                ),
                MindMapItem(
                    title = "HelloWorld 2",
                    percentageOffset = Offset(
                        x = 1f,
                        y = -0.5f
                    )
                ),
                MindMapItem(
                    title = "HelloWorld 3",
                    percentageOffset = Offset(
                        x = 0.3f,
                        y = -0.75f
                    )
                ),
                MindMapItem(
                    title = "HelloWorld 4",
                    percentageOffset = Offset(
                        x = .5f,
                        y = .05f
                    )
                ),
                MindMapItem(
                    title = "HelloWorld 5",
                    percentageOffset = Offset(
                        x = .25f,
                        y = .25f
                    )
                )


            )
        }
            var mindMapOffSet by remember {
                mutableStateOf(IntOffset.Zero)
            }
            LazyMindMap(
                items = mindMapItems,
                mindMapOffSet = mindMapOffSet,
                onDrag = {delta ->
                    mindMapOffSet += delta
                },

                modifier = Modifier
                    .fillMaxSize()
                    .safeDrawingPadding()
            )

        }
    }
}

