package com.example.jetpackcompose.uiComponent

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    SetConstraintLayout()
}

@Composable
fun SetConstraintLayout(){
    val constraints = ConstraintSet() {
        val greenBox = createRefFor("greenbox")
        val redBox = createRefFor("redbox")
        val yellowBox = createRefFor("yellowbox")
        val blueBox = createRefFor("bluebox")

        constrain(greenBox){
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        constrain(redBox){
            top.linkTo(parent.top)
            start.linkTo(greenBox.end)
            end.linkTo(parent.end)
//            width = Dimension.fillToConstraints
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        constrain(yellowBox){
            top.linkTo(greenBox.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        constrain(blueBox){
            top.linkTo(yellowBox.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
//            width = Dimension.fillToConstraints
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

//        To create horizontal chain
        createHorizontalChain(greenBox,redBox, chainStyle = ChainStyle.Spread)
//        To create Vertical chain
        createVerticalChain(yellowBox,blueBox, chainStyle = ChainStyle.Spread)
    }
    ConstraintLayout(constraints, modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier
            .background(Color.Green)
            .layoutId("greenbox")
        )
        Box(modifier = Modifier
            .background(Color.Red)
            .layoutId("redbox")
        )

        Box(modifier = Modifier
            .background(Color.Yellow)
            .layoutId("yellowbox")
        )
        Box(modifier = Modifier
            .background(Color.Blue)
            .layoutId("bluebox")
        )

    }
}