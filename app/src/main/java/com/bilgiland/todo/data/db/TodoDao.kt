package com.bilgiland.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bilgiland.todo.data.model.TodoModel

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoModel)

    @Query("select * from TodoModel")
    fun getAllTodo(): LiveData<List<TodoModel>>

    @Delete
    suspend fun deleteTodo(todo: TodoModel)

    @Query("delete from TodoModel")
    suspend fun deleteAll()

    @Query("update todomodel SET done=:done where id=:id")
    suspend fun update(id: Int, done: Int)

}