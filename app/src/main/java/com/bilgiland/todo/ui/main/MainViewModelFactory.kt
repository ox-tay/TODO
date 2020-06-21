package com.bilgiland.todo.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bilgiland.todo.data.repository.TodoRepo

/**
 * provide requirement of viewModel
 */
class MainViewModelFactory(private val repository: TodoRepo): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}