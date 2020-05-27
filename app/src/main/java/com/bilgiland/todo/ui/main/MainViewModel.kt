package com.bilgiland.todo.ui.main

import androidx.lifecycle.ViewModel
import com.bilgiland.todo.data.model.TodoModel
import com.bilgiland.todo.data.repository.TodoRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private var repository: TodoRepo
) : ViewModel() {

    fun getAll() = repository.getAllTodo()

    fun insert(todoModel: TodoModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(todoModel)
    }

    fun delete(todoModel: TodoModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteTodo(todoModel)
    }

    fun done(id: Int, done: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.done(id, done)
    }

    fun deleteAll() = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteAll()
    }


}