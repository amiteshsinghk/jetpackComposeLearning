package com.example.jetpackcompose.masterclass.todoScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imeNestedScroll
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ToDoScreenRoot(viewModel: ToDoViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    Log.d("ToDoScreenRoot", "viewModel: ${viewModel.hashCode()}")
    val state by viewModel.state.collectAsStateWithLifecycle()
    val title by viewModel.title.collectAsStateWithLifecycle()
    val description by viewModel.description.collectAsStateWithLifecycle()
    ToDoScreen(
        list = state.todoItems,
        onAction = { viewModel.onAction(it) },
        title = { viewModel.title.value = it },
        description = { viewModel.description.value = it },
        titleText = title,
        descriptionText = description

    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ToDoScreen(
    list: List<ToDoEntity>,
    onAction: (ToDoAction) -> Unit,
    title: (title: String) -> Unit,
    description: (description: String) -> Unit,
    titleText: String,
    descriptionText: String
) {

    Column(
        modifier = Modifier
            .safeDrawingPadding()
            .background(Color.White)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .weight(1f)
                .imeNestedScroll()
        ) {
            items(list) { toDoItem ->
                ToDoItemScreen(
                    toDoEntity = toDoItem,
                    onAction = onAction,
                    modifier = Modifier
                )

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = titleText,
                    onValueChange = {
                        title(it)
                    },
                    label = { Text("Enter title") }
                )

                TextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = descriptionText,
                    onValueChange = {
                        description(it)
                    },
                    label = { Text("Enter description") },
                )
            }

            Button(
                onClick = { onAction(ToDoAction.AddItem) },
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = "Add"
                )
            }
        }
    }
}

@Composable
fun ToDoItemScreen(
    toDoEntity: ToDoEntity,
    onAction: (ToDoAction) -> Unit,
    modifier: Modifier
) {
    Log.d("ToDoViewModel", "ToDoScreen :: ToDoItemScreen: $toDoEntity")
    Row(modifier = modifier.padding(16.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Text(
                text = toDoEntity.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                style = TextStyle(
                    textDecoration = if (toDoEntity.isChecked) TextDecoration.LineThrough else TextDecoration.None
                )
            )
            Text(
                text = toDoEntity.description,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                fontSize = 16.sp,
                style = TextStyle(
                    textDecoration = if (toDoEntity.isChecked) TextDecoration.LineThrough else TextDecoration.None
                )
            )
        }
        Checkbox(checked = toDoEntity.isChecked,
            onCheckedChange = { onAction(ToDoAction.toggleItem(toDoEntity)) }
        )
        IconButton(onClick = { onAction(ToDoAction.removeItem(toDoEntity)) }) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = null
            )
        }
    }
}

var list = (1..100).toList().map {
    ToDoEntity(
        title = "Title $it",
        description = "Description Description Description Description Description $it",
        isChecked = false
    )
}

@Preview(
    device = Devices.NEXUS_10
)
@Composable
private fun ToDoScreenTabletPreview() {
    ToDoScreen(list, {}, {}, {}, "", "")
}


@Preview
@Composable
private fun ToDoScreenPreview() {
    ToDoScreen(list, {}, {}, {}, "", "")
}

@Preview(
    name = "Landscape Preview",
    showBackground = true,
    widthDp = 800,
    heightDp = 400,
    device = "spec:width=800dp,height=400dp,orientation=landscape"
)
@Composable
private fun ToDoScreenLandcapePreview() {
    ToDoScreen(list, {}, {}, {}, "", "")
}