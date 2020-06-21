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

/**
 * Main view model use it for transformation data as MVVM arch
 */
class MainViewModel(
    private var repository: TodoRepo
) : ViewModel() {

    private val _searchStringLiveData = MutableLiveData<String>()

    init {
        _searchStringLiveData.value = ""
    }

    /**
     * get all tasks
     * if search not active return all if not return filter list
     */
    fun getAll(): LiveData<List<TodoModel>> = Transformations.switchMap(_searchStringLiveData) {
        if (_searchStringLiveData.value != "")
            repository.getSearchTodo(it)
        else
            repository.getAllTodo()

    }

    /**
     * insert new task
     */
    fun insert(todoModel: TodoModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.insert(todoModel)
    }

    /**
     * delete task
     */
    fun delete(todoModel: TodoModel) = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteTodo(todoModel)
    }

    /**
     * done or open task
     */
    fun done(id: Int, done: Int) = CoroutineScope(Dispatchers.Main).launch {
        repository.done(id, done)
    }

    /**
     * delete all tasks
     */
    fun deleteAll() = CoroutineScope(Dispatchers.Main).launch {
        repository.deleteAll()
    }

    /**
     * put search text on mutable live data
     */
    fun searchTextChanged(name: String) {
        _searchStringLiveData.value = name
    }
}
