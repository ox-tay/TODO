package com.bilgiland.todo.ui.main

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.bilgiland.todo.R
import com.bilgiland.todo.utility.toast
import kotlinx.android.synthetic.main.add_todo.*


class AddDialog(context: Context, private var addDialog: AddTodoListener) :
    AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        btn_add.setOnClickListener {

            val todo: String = edt_todo.text.toString()

            if (todo.isNotEmpty()) {
                addDialog.onAddButtonClicked(todo)
                dismiss()
            } else {
                context.toast(context.getString(R.string.enter_name))
            }
        }

        btn_cancel.setOnClickListener { dismiss() }

    }
}