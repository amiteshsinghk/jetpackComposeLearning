package com.example.jetpackcompose.masterclass.measurement.dynamicMindMap

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme



@Composable
fun LazyMindMapRoot(modifier: Modifier){
    var counter = remember { 
        mutableStateOf(0)
    }
    var counter2 = remember {
        mutableStateOf(0)
    }
    var checked = remember { 
        mutableStateOf(false)
    }

    var mindMapItems = remember {
        listOf(
            MindMapItems(
                content = {
                    IncrementText(
                        IncrementTitle = counter.value.toString(),
                        increment = {
                            counter.value ++
                        },
                        decrement = {
                            counter.value--
                                    }
                    )
                },
                constraint = Constraints(
                    maxWidth = 2000,
                    maxHeight = 1500
                ),
                percentageOffset = Offset(
                    x = 0f,
                    y = 0f
                )
            ),
            MindMapItems(
                content = {
                    ToDo(
                        checkboxTitle = "To Do Item",
                        checkboxDes = "This is description",
                        checked = checked.value,
                        onCheckedChange = {
                            checked.value = it
                        }
                    )
                },
                constraint = Constraints(
                    maxWidth = 2000,
                    maxHeight = 1500
                ),
                percentageOffset = Offset(
                    x = 1f,
                    y = -0.5f
                )
            ),
            MindMapItems(
                content = {
                    IncrementText(
                        IncrementTitle = counter2.value.toString(),
                        increment = {
                            counter2.value ++
                        },
                        decrement = {
                            counter2.value--
                        }
                    )
                },
                constraint = Constraints(
                    maxWidth = 2000,
                    maxHeight = 1500
                ),
                percentageOffset = Offset(
                    x = 1f,
                    y = 0f
                )
            ),

        )
    }
    var mindMapOffSet by remember {
        mutableStateOf(IntOffset.Zero)
    }
    LazyMindMap(
        items = mindMapItems,
        mindMapOffSet = mindMapOffSet,
        onDrag = { delta ->
            mindMapOffSet += delta
        },

        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding()
    )


}

@Composable
fun IncrementText(
    IncrementTitle: String,
    increment: (Int) -> Unit,
    decrement: (Int) -> Unit,
    modifier: Modifier = Modifier
){

    Box(
        modifier = modifier
            .widthIn(min = 50.dp, max = 250.dp)
            .heightIn(min = 50.dp, max = 250.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray
            )
            .padding(16.dp)


    ){
        Column(modifier = Modifier
            .wrapContentSize()
            .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = IncrementTitle,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,

            )
            Row (
                modifier = Modifier,
                horizontalArrangement = Arrangement.SpaceEvenly

            ){
                Button(
                    onClick = {
                        decrement(1)
                    }
                ) {
                    Text(text = "Dec")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = {
                        increment(1)
                    }
                ) {
                    Text(text = "Inc")
                }
            }

        }
    }
}

@Composable
fun ToDo(
    checkboxTitle: String,
    checkboxDes: String,
    checked: Boolean,
    onCheckedChange:(Boolean)-> Unit,
    modifier: Modifier = Modifier
){
    Box(
        modifier = Modifier
            .widthIn(min = 50.dp, max = 250.dp)
            .heightIn(min = 50.dp, max = 250.dp)
            .border(
                width = 2.dp,
                color = Color.LightGray
            )
            .padding(16.dp)


    ) {
        Row (
            modifier = modifier
                .wrapContentSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            Column(
                modifier = Modifier
                    .wrapContentSize()
                    .weight(1f)
            ) {
                Text(
                    text = checkboxTitle,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 14.sp,
                   textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None
                )
                Text(
                    text = checkboxDes,
                    fontSize = 12.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textDecoration = if (checked) TextDecoration.LineThrough else TextDecoration.None
                )
            }
            Spacer(Modifier.height(16.dp))

            Checkbox(
                checked = checked,
                onCheckedChange= { onCheckedChange(it) }
            )

        }
    }

}

@Preview(
    showBackground = true
)
@Composable
private fun IncrementTextPreview(){
    JetpackComposeTheme {
//        IncrementText(
//            IncrementTitle = "5",{},{}
//        )

        ToDo(
            checkboxTitle = "MindMapToDo MindMapToDo MindMapToDo MindMapToDo MindMapToDo MindMapToDo",
            checkboxDes = "Description",
            checked = true,
            onCheckedChange = {true}
        )
    }
}
