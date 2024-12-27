package com.example.jetpackcompose.masterclass.todoScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ToDoViewModel() : ViewModel() {
    var title = MutableStateFlow<String>(
        value = ""
    )
    var description = MutableStateFlow<String>("")
    private val _state = MutableStateFlow(ToDoState())
    val state = _state.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ToDoState()
    )

    fun onAction(action: ToDoAction) {
        when (action) {
            is ToDoAction.AddItem -> {
                if (title.value.isNotEmpty() && description.value.isNotEmpty()) {
                    val item = ToDoEntity(
                        title = title.value,
                        description = description.value,
                        isChecked = false
                    )
                    _state.update { stateItem ->
                        stateItem.copy(
                            todoItems = stateItem.todoItems + item
                        )
                    }
                    title.value = ""
                    description.value = ""
                }
            }

            is ToDoAction.removeItem -> {
                _state.update { stateItem ->
                    stateItem.copy(
                        todoItems = stateItem.todoItems - action.item
                    )
                }
            }

            is ToDoAction.toggleItem -> {
                Log.d("ToDoViewModel", "onAction: ${action.item}")
                _state.update { stateItem ->
                    stateItem.copy(
                        todoItems = stateItem.todoItems.map { toDo ->
                            if (toDo == action.item) {
                                Log.d("ToDoViewModel", "Inside if :: onAction: ${action.item}")
                                toDo.copy(isChecked = !toDo.isChecked)
                            } else {
                                Log.d("ToDoViewModel", "Inside else :: onAction: ${action.item}")
                                toDo
                            }
                        }
                    )
                }

            }
        }
    }
}