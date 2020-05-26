package com.bilgiland.todo.data.repository

import androidx.lifecycle.MutableLiveData
import com.bilgiland.todo.data.db.TodoDb
import com.bilgiland.todo.data.model.TodoModel

class TodoRepo(private val db: TodoDb) {

    suspend fun deleteTodo(todoModel: TodoModel) = db.todoDao().deleteTodo(todoModel)

    suspend fun deleteAll() = db.todoDao().deleteAll()

    suspend fun insert(todoModel: TodoModel) = db.todoDao().insert(todoModel)

    fun getAllTodo() = db.todoDao().getAllTodo()

}
