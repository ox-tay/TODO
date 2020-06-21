package com.bilgiland.todo.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatDialog
import com.bilgiland.todo.R
import kotlinx.android.synthetic.main.add_todo.*


class AddDialog(context: Context, private var addDialog: AddTodoListener) :
    AppCompatDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_todo)

        edt_todo.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                addClicked()
                true
            } else {
                false
            }
        }

        btn_add.setOnClickListener {
            addClicked()
        }

        btn_cancel.setOnClickListener { dismiss() }

    }

    // add clicked
    private fun addClicked() {
        val todo: String = edt_todo.text.toString()

        if (todo.isNotEmpty()) {
            addDialog.onAddButtonClicked(todo)
            dismiss()
        } else {
            textField_todo.error = context.getString(R.string.enter_name)
        }
    }
}