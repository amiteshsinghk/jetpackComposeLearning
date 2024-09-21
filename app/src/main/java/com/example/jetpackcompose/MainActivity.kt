package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.uiComponent.GetImageCard

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val painter = painterResource(id = R.drawable.kermit)
            val description = "Kermit is a great picture"
            val title = "Kermit is a great picture"

            Box(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(16.dp)) {
                GetImageCard( painter = painter,
                    contentDescription = description,
                    title = title,
                    height = 200, width = 200,
                    cornerRadius = 15)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    val painter = painterResource(id = R.drawable.kermit)
    val description = "Kermit is a great picture"
    val title = "Kermit is a great picture"
    Box(modifier = Modifier
        .fillMaxWidth(0.5f)
        .padding(16.dp)) {
        GetImageCard( painter = painter,
            contentDescription = description,
            title = title,
            height = 200, width = 200,
            cornerRadius = 15)
    }
}