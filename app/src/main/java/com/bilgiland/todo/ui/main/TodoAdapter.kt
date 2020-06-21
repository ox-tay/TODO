package com.bilgiland.todo.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bilgiland.todo.R
import com.bilgiland.todo.data.model.TodoModel
import com.bilgiland.todo.databinding.TodoItemBinding

/***
 * adapter of tasks recyclerview
 */
class TodoAdapter(private val adapterListener: AdapterListener) :
    ListAdapter<TodoModel, TodoAdapter.TodoViewHolder>(TodoCallBack()) {

    /**
     * use it for determine difference
     */
    class TodoCallBack : DiffUtil.ItemCallback<TodoModel>() {
        override fun areItemsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TodoModel, newItem: TodoModel): Boolean {
            return oldItem == newItem
        }

    }

    /**
     * view holder of todos
     */
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

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.todoItemBinding.model = getItem(position)

        holder.todoItemBinding.checkBox.setOnClickListener {
            var done = 0

            if (holder.todoItemBinding.checkBox.isChecked) {
                done = 1
            }

            adapterListener.onDoneClicked(getItem(holder.layoutPosition).id!!, done)
        }
        holder.todoItemBinding.imgDelete.setOnClickListener {
            adapterListener.onDeleteClicked(getItem(holder.layoutPosition))
        }
    }



}