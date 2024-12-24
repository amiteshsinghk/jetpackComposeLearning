package com.example.jetpackcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.example.jetpackcompose.masterclass.HotelBookingScreen
import com.example.jetpackcompose.masterclass.todoScreen.ToDoScreenRoot
import com.example.jetpackcompose.masterclass.todoScreen.ToDoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val viewModel =  ToDoViewModel()
//        Log.d("MainActivity","onCreate: ${viewModel.hashCode()}")
        enableEdgeToEdge()
        setContent {
//                HotelBookingScreen()

            ToDoScreenRoot()
        }
    }
}

