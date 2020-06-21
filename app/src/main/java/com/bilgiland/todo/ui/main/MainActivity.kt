package com.bilgiland.todo.ui.main

import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilgiland.todo.R
import com.bilgiland.todo.data.model.TodoModel
import com.bilgiland.todo.ui.bases.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

/**
 * Main Activity show list of tasks
 */
class MainActivity : BaseActivity(), KodeinAware, AdapterListener {

    //use kodein for DI
    override val kodein by kodein()

    private val factory: MainViewModelFactory by instance<MainViewModelFactory>()
    private val recAdapter: TodoAdapter by instance<TodoAdapter>()

    private lateinit var viewModel: MainViewModel

    override fun restInti() {
        intiUi()
        manageClick()
    }

    override fun setViewModel() {
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
    }

    override fun getLayout(): Int = R.layout.activity_main


    // manage click on view
    private fun manageClick() {
        fab_add.setOnClickListener {
            fabClicked()
        }

        //delete all tasks
        img_delete_all.setOnClickListener {
            deleteAllClicked()
        }

    }

    private fun deleteAllClicked() {
        viewModel.deleteAll()
    }

    // add new task
    private fun fabClicked() {
        clearFocusFromSearch()
        AddDialog(this, object : AddTodoListener {
            override fun onAddButtonClicked(name: String) {
                viewModel.insert(TodoModel(name, 0))
            }
        }).show()
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
            recAdapter.submitList(ArrayList(it))
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
