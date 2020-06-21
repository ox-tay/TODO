package com.bilgiland.todo.ui.main

/**
 * manage add new task
 */
interface AddTodoListener {
    /**
     * add new task
     */
    fun onAddButtonClicked(name: String)
}