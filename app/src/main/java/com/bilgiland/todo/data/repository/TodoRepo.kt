package com.bilgiland.todo.data.repository

import com.bilgiland.todo.data.db.TodoDb
import com.bilgiland.todo.data.model.TodoModel

/**
 * repository of tasks
 */
class TodoRepo(private val db: TodoDb) {

    // delete specific model
    suspend fun deleteTodo(todoModel: TodoModel) = db.todoDao().deleteTodo(todoModel)

    // delete all todos
    suspend fun deleteAll() = db.todoDao().deleteAll()

    // insert new todos
    suspend fun insert(todoModel: TodoModel) = db.todoDao().insert(todoModel)

    // done or open it again
    suspend fun done(id: Int, done: Int) = db.todoDao().update(id, done)

    // get all todos
    fun getAllTodo() = db.todoDao().getAllTodo()

    // get todos by search
    fun getSearchTodo(name: String) = db.todoDao().getSearch(name)


}
