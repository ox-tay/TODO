package com.bilgiland.todo.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.bilgiland.todo.data.model.TodoModel

@Database(entities = [TodoModel::class], version = 1)
abstract class TodoDb : RoomDatabase() {
    abstract fun todoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: TodoDb? = null
        private var LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK)
        {
            instance ?: buildDataBase(context).also {
                instance = it
            }
        }

        private fun buildDataBase(context: Context) =
            Room.databaseBuilder(context.applicationContext, TodoDb::class.java, "todo.db").build()
    }


}