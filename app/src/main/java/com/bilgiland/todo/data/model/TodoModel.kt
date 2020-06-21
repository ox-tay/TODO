package com.bilgiland.todo.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Model of todos
 */
@Entity
data class TodoModel(
    var name: String,
    @ColumnInfo(defaultValue = "0")
    var done: Int
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}