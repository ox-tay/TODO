package com.bilgiland.todo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bilgiland.todo.data.model.TodoModel

@Dao
interface TodoDao {

    // insert new mode
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(todo: TodoModel)

    // get all models
    @Query("select * from TodoModel")
    fun getAllTodo(): LiveData<List<TodoModel>>

    // get all models by search
    @Query("select * from TodoModel where name like '%' || :name || '%'")
    fun getSearch(name: String): LiveData<List<TodoModel>>

    // delete mdoel
    @Delete
    suspend fun deleteTodo(todo: TodoModel)

    // delete all mode
    @Query("delete from TodoModel")
    suspend fun deleteAll()

    // change status of done in update model
    @Query("update todomodel SET done=:done where id=:id")
    suspend fun update(id: Int, done: Int)

}