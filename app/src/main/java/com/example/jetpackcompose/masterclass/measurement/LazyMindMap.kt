package com.example.jetpackcompose.masterclass.measurement

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.round
import androidx.compose.ui.util.fastMapIndexedNotNull
import kotlin.math.roundToInt

data class MindMapItem(
    val title: String,
    val percentageOffset: Offset
)

/*
* Actual Placeable for the item
* */
private data class  ProcessedMindMapItem(
    val placeable: Placeable,
    val finalXPosition: Int,
    val finalYPosition: Int
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyMindMap(
    items: List<MindMapItem>,
    mindMapOffSet: IntOffset = IntOffset.Zero,
    onDrag: (delta: IntOffset) -> Unit,
    itemModifier: Modifier = Modifier,
    modifier: Modifier = Modifier
){
    LazyLayout(
        modifier = Modifier
            .draggable2D(state = rememberDraggable2DState{ delta ->
                onDrag(delta.round())
            }),
       itemProvider = {
           object : LazyLayoutItemProvider {
               override val itemCount: Int
                   get() = items.size

               @Composable
               override fun Item(index: Int, key: Any) {
                   Text(
                       text = "ABS",
                       textAlign = TextAlign.Center,
                       overflow = TextOverflow.Ellipsis,
                       maxLines = 2,
                       modifier = itemModifier
                           .widthIn(min = 50.dp, max = 150.dp)
                           .heightIn(min = 50.dp, max = 150.dp)
                           .border(
                               width = 2.dp,
                               color = Color.LightGray
                           )
                           .padding(16.dp)
                   )
               }
           }
       }
    ) { constraints ->
        val layoutWidth = constraints.maxWidth
        val layoutHeight = constraints.maxHeight

        val visibleArea = IntRect(
            left = 0,
            top = 0,
            right = layoutWidth,
            bottom = layoutHeight
        )
       val visibleItems = items.fastMapIndexedNotNull { index, mindMapItem ->
            val finalXPosition = ((mindMapItem.percentageOffset.x * layoutWidth)+ layoutWidth/2 + mindMapOffSet.x).roundToInt()
            val finalYPosition = ((mindMapItem.percentageOffset.y * layoutHeight)+ layoutHeight/2 + mindMapOffSet.y).roundToInt()

            val maxItemWidth = 150.dp.roundToPx()
            val maxItemHeight = 75.dp.roundToPx()
            val extendedItemBounds = IntRect(
                left = finalXPosition - maxItemWidth /2,
                top = finalYPosition - maxItemHeight /2,
                right = finalXPosition + 3 * (maxItemWidth / 2),
                bottom = finalYPosition + 3 * (maxItemHeight / 2)

            )

           if (visibleArea.overlaps(extendedItemBounds)){
               val placeable = measure(
                   index = index,
                   constraints = Constraints()
               ).first()
               ProcessedMindMapItem(
                   placeable = placeable,
                   finalXPosition = finalXPosition - placeable.width / 2,
                   finalYPosition = finalYPosition - placeable.height / 2
               )

               } else null
       }

        layout(constraints.maxWidth,constraints.maxHeight){
            visibleItems.forEach {
                it.placeable.place(it.finalXPosition,it.finalYPosition)
            }
        }
    }

}

//@Preview
//@Composable
//private fun LazyMindMapPreview(){
//    JetpackComposeTheme {
//        LazyMindMap()
//    }
//}