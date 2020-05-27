package com.bilgiland.todo.ui.main

import com.bilgiland.todo.data.model.TodoModel

interface AdapterListener{
    fun onDeleteClicked(todoModel: TodoModel)
    fun onDoneClicked(todoModel: Int, done: Int)
}