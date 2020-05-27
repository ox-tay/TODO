package com.bilgiland.todo.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.bilgiland.todo.data.model.TodoModel
import com.bilgiland.todo.data.repository.TodoRepo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    private var repository: TodoRepo
) : ViewModel() {

    private val _searchStringLiveData = MutableLiveData<String>()

    init {
        _searchStringLiveData.value = ""
    }

    fun getAll(): LiveData<List<TodoModel>> = Transformations.switchMap(_searchStringLiveData) {
        if(_searchStringLiveData.value!="")
            repository.getSearchTodo(it)
        else
            repository.getAllTodo()

    }

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

        fun searchTextChanged(name: String) {
            _searchStringLiveData.value = name
        }
    }
