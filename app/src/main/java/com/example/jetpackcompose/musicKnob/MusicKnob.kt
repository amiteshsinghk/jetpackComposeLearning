package com.example.jetpackcompose.musicKnob

import android.util.Log
import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpackcompose.R
import kotlin.math.PI
import kotlin.math.atan2


@Composable
fun VolumeBar(
    modifier: Modifier = Modifier,
    activeBars: Int = 0,// to show the green
    barCount: Int = 10
){
    BoxWithConstraints(
        contentAlignment = Alignment.Center,
        modifier = modifier
    ) {
        val barWidth = remember {
//            To make the same space between two bar as the width of the bar.
            constraints.maxWidth / (2f * barCount)
        }
        Canvas(modifier = modifier) {
            for (i in 0 until barCount){
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.DarkGray,
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f),
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    cornerRadius = CornerRadius(0f)
                )
            }
        }
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MusicKnob(
    modifier: Modifier = Modifier,
    limitingAngle: Float = 25f,
    onValueChange: (Float) -> Unit
) {
    var rotation by remember {
        mutableFloatStateOf(limitingAngle)
    }
    var touchX by remember {
        mutableFloatStateOf(0f)
    }
    var touchY by remember {
        mutableFloatStateOf(0f)
    }
    var centreX by remember {
        mutableFloatStateOf(0f)
    }
    var centreY by remember {
        mutableFloatStateOf(0f)
    }

    Image(
        painter = painterResource(id = R.drawable.music_knob),
        contentDescription = "Music knob",
        modifier = modifier
            .fillMaxSize()
            .onGloballyPositioned {
                val windowsBounds = it.boundsInWindow()
                centreX = windowsBounds.size.width / 2f
                centreY = windowsBounds.size.height / 2f
            }
            .pointerInteropFilter { touchEvent ->
                touchX = touchEvent.x
                touchY = touchEvent.y
                // Here minus is used to have the rotation clockwise else it will be anti clockwise.
                val angle = -atan2(centreX-touchX, centreY - touchY) *(180f / PI).toFloat()
                //- need to add on knob
                Log.d("MusicKnob"," touchEvent.action :: ${touchEvent.action} :: angle :: $angle ")
                when(touchEvent.action){
                    MotionEvent.ACTION_DOWN,
                        MotionEvent.ACTION_MOVE ->{
                        if (angle !in -limitingAngle..limitingAngle){
//                           When the knob is rotated past 180 degrees, the angle becomes negative. To keep it positive, we add 360 degrees to the result. So the rotation angle is in between 0-360
                           val fixedAngle = if(angle in -180f..-limitingAngle){
                               360f + angle
                           } else{
                               angle
                           }
                             rotation = fixedAngle
                            val percent = (fixedAngle - limitingAngle) / (360f - 2 * limitingAngle)
                            onValueChange(percent)
                            true
                        } else false
                    }
                    else -> false
                }
            }
            .rotate(rotation)
    )

}


@Preview(showBackground = true)
@Composable
fun GreetingPreviewMusicKnob() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101010))
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(1.dp, Color.Green, RoundedCornerShape(10.dp))
                .padding(30.dp)
        ) {
            var volume by remember {
                mutableStateOf(0f)
            }
            val barCount = 20
            MusicKnob(
                modifier = Modifier.size(100.dp)
            ) {
                volume = it
            }
            Spacer(modifier = Modifier.width(20.dp))
            VolumeBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(30.dp),
                activeBars = (barCount * volume).toInt(),
                barCount = barCount
            )
        }
    }
}