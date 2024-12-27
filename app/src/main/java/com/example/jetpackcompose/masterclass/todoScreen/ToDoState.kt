package com.example.jetpackcompose.masterclass.todoScreen

data class ToDoState (
    val todoItems: List<ToDoEntity> = list1,
    val title: String? = null,
    val description: String? = null
)

var list1 = (1..10).toList().map {
    ToDoEntity(
        title = "Title $it",
        description = "Description Description Description Description Description $it",
        isChecked = false
    )
}