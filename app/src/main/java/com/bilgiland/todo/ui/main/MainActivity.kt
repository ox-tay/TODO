package com.bilgiland.todo.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilgiland.todo.R
import com.bilgiland.todo.data.model.TodoModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware, AdapterListener {

    override val kodein by kodein()

    private val factory: MainViewModelFactory by instance<MainViewModelFactory>()
    private val recAdapter: TodoAdapter by instance<TodoAdapter>()

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        intiUi()
        manageClick()
    }

    private fun manageClick() {
        // add new task
        fab_add.setOnClickListener {
            clearFocusFromSearch()
            AddDialog(this, object : AddTodoListener {
                override fun onAddButtonClicked(name: String) {
                    viewModel.insert(TodoModel(name, 0))
                }
            }).show()
        }

        //delete all tasks
        img_delete_all.setOnClickListener {
            viewModel.deleteAll()
        }
    }

    // clear focus from search bar
    // add focus to dummy view
    private fun clearFocusFromSearch() {
        edt_search.clearFocus()
        dummy_view.requestFocus()
    }


    private fun intiUi() {
        rec_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = recAdapter
        }

        viewModel.getAll().observe(this, Observer {
            recAdapter.add(ArrayList(it))
        })

        edt_search.addTextChangedListener {
            viewModel.searchTextChanged(it.toString())
        }

    }

    override fun onDeleteClicked(todoModel: TodoModel) {
        viewModel.delete(todoModel)
    }

    override fun onDoneClicked(id: Int, done: Int) {
        viewModel.done(id, done)
    }
}
