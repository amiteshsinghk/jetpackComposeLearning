package com.example.jetpackcompose.masterclass.todoScreen

sealed interface ToDoAction {
    object AddItem : ToDoAction
    data class removeItem(val item: ToDoEntity) : ToDoAction
    data class toggleItem(val item: ToDoEntity) : ToDoAction
}
