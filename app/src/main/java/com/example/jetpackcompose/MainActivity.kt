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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.masterclass.HotelBookingScreen
import com.example.jetpackcompose.masterclass.SubComposePageRow
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
//                HotelBookingScreen()
            val currentPage = remember {
                mutableIntStateOf(0)
            }
            Column(modifier = Modifier
                .fillMaxSize()
                .safeDrawingPadding()) {
                SubComposePageRow(
                    page = currentPage.value,
                    modifier = Modifier
                ) {
                    for (i in 0..1000) {
                        Box(
                            modifier = Modifier
                                .width(Random.nextInt(300).dp)
                                .height(100.dp)
                                .background(Color(Random.nextInt()))
                        )
                    }
                }
                Button(
                    onClick = { currentPage.value++ }
                ) {
                    Text(text = "Next")
                }
            }

//            ToDoScreenRoot()
        }
    }
}

