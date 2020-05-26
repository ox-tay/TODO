package com.bilgiland.todo.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TodoModel(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var name: String
)