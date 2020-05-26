package com.bilgiland.todo.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bilgiland.todo.R
import com.bilgiland.todo.data.model.TodoModel
import com.bilgiland.todo.databinding.TodoItemBinding

class TodoAdapter :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var list: ArrayList<TodoModel> = ArrayList()

    fun add(list: ArrayList<TodoModel>) {
        this.list = list
        notifyDataSetChanged()
    }

    inner class TodoViewHolder(val todoItemBinding: TodoItemBinding) :
        RecyclerView.ViewHolder(todoItemBinding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.todo_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.todoItemBinding.model = list[position]
    }

}