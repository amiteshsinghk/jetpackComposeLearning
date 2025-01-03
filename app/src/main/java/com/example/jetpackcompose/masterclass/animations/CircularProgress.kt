package com.example.jetpackcompose.masterclass.animations

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.round

@Composable
fun CircularProgress(
    value: Float,
    maxValue: Float,
    unit: String,
    color: Color = Color.Gray,
    backgroundColor: Color = Color.LightGray,
    modifier: Modifier = Modifier,
    strokeWidth: Dp = 10.dp
){
    val animatedValue by animateFloatAsState(
        targetValue = value,
        label = "value animation",
        animationSpec = tween(durationMillis = 3000)
    )

    Box(
        modifier = modifier
            .drawBehind {
                drawArc(
                    color = backgroundColor,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap =  StrokeCap.Round
                    ),
                    startAngle = -90f,
                    sweepAngle = 360f,
                    useCenter = false
                )
            }
            .drawBehind {
                drawArc(
                    color = color,
                    style = Stroke(
                        width = strokeWidth.toPx(),
                        cap =  StrokeCap.Round
                    ),
                    startAngle = -90f,
                    sweepAngle = (animatedValue/maxValue) * 360f,
                    useCenter = false
                )
            }
        ,
        contentAlignment = Alignment.Center
    ){
        Text(
            text = (round(animatedValue * 10f) / 10f).toString() + unit,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@Composable
fun LinearProgress(
    value: Float,
    maxValue: Float,
    unit: String,
    color: Color = Color.Gray,
    backgroundColor: Color = Color.LightGray,
    modifier: Modifier = Modifier,
    strokeWidth: Float = 20f
){
    val animatedValue by animateFloatAsState(
        targetValue = value,
        label = "value animation",
        animationSpec = tween(durationMillis = 3000)
    )

    Box(
        modifier = modifier
            .drawBehind {
                drawLine(
                    color = backgroundColor,
                    start = Offset.Zero,
                    end = Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )
            }
            .drawBehind {
                drawLine(
                    color = color,
                    start = Offset.Zero,
                    end = Offset((size.width/maxValue)*animatedValue, 0f),
                    strokeWidth = strokeWidth
                )
            }

        ,
        contentAlignment = Alignment.Center
    ){
        Text(
            text = (round(animatedValue * 10f) / 10f).toString() + unit,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Preview(showBackground = true)
@Composable
private fun ValueDisplayPreview() {
    var value by remember {
        mutableFloatStateOf(0f)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgress(
            value = value,
            maxValue = 100f,
            unit = "%",
            modifier = Modifier
                .size(150.dp)
        )
        Spacer(Modifier.height(16.dp))
        LinearProgress(
            value = value,
            maxValue = 100f,
            unit = "%",

            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
        Spacer(Modifier.height(16.dp))
        FlowRow (
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { value = 0f }
            ) {
                Text("0%")
            }
            Button(
                onClick = { value = 10f }
            ) {
                Text("10%")
            }
            Button(
                onClick = { value = 50f }
            ) {
                Text("50%")
            }
            Button(
                onClick = { value = 90f }
            ) {
                Text("90%")
            }
            Button(
                onClick = { value = 100f }
            ) {
                Text("100%")
            }
        }
    }
}