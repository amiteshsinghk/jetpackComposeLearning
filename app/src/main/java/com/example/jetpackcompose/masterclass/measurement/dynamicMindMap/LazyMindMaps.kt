package com.example.jetpackcompose.masterclass.measurement.dynamicMindMap


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.draggable2D
import androidx.compose.foundation.gestures.rememberDraggable2DState
import androidx.compose.foundation.lazy.layout.LazyLayout
import androidx.compose.foundation.lazy.layout.LazyLayoutItemProvider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntRect
import androidx.compose.ui.unit.round
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastMapIndexedNotNull
import kotlin.math.roundToInt

data class MindMapItems(
    val content: @Composable () -> Unit,
    val constraint: Constraints,
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
    items: List<MindMapItems>,
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
                    val item =items[index]
                    Layout(
                        content = item.content,
                        measurePolicy = { measurables, constraints ->
                            val placeable = measurables.map {
                                it.measure(item.constraint)
                            }
                            val maxWidth = placeable.maxOfOrNull { it.width } ?: 0
                            val minWidth = placeable.maxOfOrNull { it.height } ?: 0
                            layout(maxWidth , minWidth){
                                placeable.forEach {
                                    it.placeRelative(0,0)
                                }
                            }
                        }
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

            val maxItemWidth = mindMapItem.constraint.maxWidth
            val maxItemHeight = mindMapItem.constraint.maxHeight
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
                )
                placeable.map{
                    ProcessedMindMapItem(
                        placeable = it,
                        finalXPosition = finalXPosition /*- it.width / 2*/,
                        finalYPosition = finalYPosition /*- it.height / 2*/
                    )
                }

            } else null
        }

        layout(constraints.maxWidth,constraints.maxHeight){
            visibleItems.flatten().fastForEach { item ->
                item.placeable.place(
                    item.finalXPosition - item.placeable.width / 2,
                    item.finalYPosition - item.placeable.height / 2
                )
            }
        }
    }

}
