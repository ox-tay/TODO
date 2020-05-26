package com.bilgiland.todo.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bilgiland.todo.data.model.TodoModel
import com.bilgiland.todo.data.repository.TodoRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private var repository: TodoRepo
) : ViewModel() {

//    private val todos = MutableLiveData<List<TodoModel>>()
//
//    fun todos(): LiveData<List<TodoModel>> = todos
//
//    fun getTodos() {
//        todos.value = repository.getAllTodo().value
//    }

    fun getAll()=repository.getAllTodo()

    fun insert(todoModel: TodoModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(todoModel)
    }


}