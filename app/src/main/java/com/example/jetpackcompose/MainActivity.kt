package com.example.jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

                ImageCard(
                    painter = painter,
                    contentDescription = description,
                    title = title,
                )
            }
        }
    }
}


@Composable
fun ImageCard(
    painter: Painter,
    contentDescription: String,
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(15.dp),
        modifier = modifier.fillMaxWidth(),// This value is working its not taking the modifier of the outer box i.e from the main activity.
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
        .height(200.dp)) {
            Image(
                painter = painter,
                contentDescription = contentDescription,
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            ),
                            startY = 300f
                        )
                    )
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(12.dp),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(text = title, style = TextStyle(fontSize = 16.sp, color = Color.White), textAlign = TextAlign.Start)
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

//        ImageCard(
//            painter = painter,
//            contentDescription = description,
//            title = title,
//        )
        GetImageCard( painter = painter,
            contentDescription = description,
            title = title,
            height = 200, width = 200,
            cornerRadius = 15)
    }
}