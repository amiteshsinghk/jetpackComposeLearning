package com.example.jetpackcompose.masterclass.animations

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpackcompose.R
import com.example.jetpackcompose.ui.theme.JetpackComposeTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionAnimation(modifier: Modifier = Modifier){
    var isExpanded by remember { mutableStateOf(false) }
    SharedTransitionLayout (modifier = modifier){
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .weight(1f),
                contentAlignment = Alignment.Center
            ){
                this@Column.AnimatedVisibility(
                    visible = !isExpanded
                ){
                    RowListIem(
                        onClick = {isExpanded = !isExpanded},
                        animatedVisibilityScope = this,
                        modifier = Modifier
                            .sharedBounds(// Bound the layout
                                sharedContentState = rememberSharedContentState(
                                    key = "row-image-kermit"
                                ),
                                animatedVisibilityScope = this
                            )
                    )
                }



            }
            Box (
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                contentAlignment = Alignment.Center
            ){
                this@Column.AnimatedVisibility(
                    visible = !isExpanded
                ){
                    ColumnListItem(
                        onClick = {
                            isExpanded = !isExpanded
                        },
                        animatedVisibilityScope = this,
                        modifier = Modifier
                            .sharedBounds(
                                sharedContentState = rememberSharedContentState(
                                    key = "row-image-kermit"
                                ),
                                animatedVisibilityScope = this
                            )
                    )
                }

            }

        }

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.ColumnListItem(
    onClick: () -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
     modifier: Modifier= Modifier){
    Column (modifier = Modifier
        .fillMaxWidth()
        .clickable {
            onClick()
        }
        .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.kermit),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .sharedElement(// bound the item
                    state = rememberSharedContentState(
                        key = "image-kermit"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                ),

        )
        Text(
            text = "Hello World",
            fontSize = 20.sp,
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(
                    key = "title-kermit"
                ),
        animatedVisibilityScope = animatedVisibilityScope
        ),
        )
        Text(
            text = "Hello World Description",
            fontSize = 14.sp,
            modifier = Modifier.sharedElement(
                state = rememberSharedContentState(
                    key = "title-description"
                ),
                animatedVisibilityScope = animatedVisibilityScope
            )
        )

    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.RowListIem(
    onClick:() -> Unit,
    animatedVisibilityScope: AnimatedVisibilityScope,
    modifier: Modifier = Modifier
){
    Row(modifier = modifier
        .height(IntrinsicSize.Min)
        .padding(16.dp)
        .clickable {
            onClick()
        }
        .sharedElement(
            state = rememberSharedContentState(
                key = "image-kermit"
            ),
            animatedVisibilityScope = animatedVisibilityScope
        ),
        horizontalArrangement = Arrangement.spacedBy(16.dp)) {
        Image(
            painter = painterResource(id = R.drawable.kermit),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(100.dp)
        )
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ){
            Text(
                text = "Hello World",
                fontSize = 20.sp,
                modifier = Modifier.sharedElement(
                    state = rememberSharedContentState(
                        key = "title-kermit"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            )
            Text(
                text = "Hello World Description",
                fontSize = 14.sp,
                modifier = Modifier.sharedElement(
                    state = rememberSharedContentState(
                        key = "title-description"
                    ),
                    animatedVisibilityScope = animatedVisibilityScope
                )
            )
        }

    }
}



@Preview(
    showBackground = true
)
@Composable
private fun SharedTransitionAnimationPreview(){
    JetpackComposeTheme {
        SharedTransitionAnimation()

    }
}