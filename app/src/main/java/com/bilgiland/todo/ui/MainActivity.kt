package com.bilgiland.todo.ui

import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bilgiland.todo.R
import com.bilgiland.todo.data.model.TodoModel
import kotlinx.android.synthetic.main.activity_main.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()

    private val factory: MainViewModelFactory by instance<MainViewModelFactory>()
    private val recAdapter: TodoAdapter by instance<TodoAdapter>()
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)

        setContentView(R.layout.activity_main)

        rec_main.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = recAdapter
        }


        viewModel.getAll().observe(this, Observer {
            recAdapter.add(ArrayList(it))
        })

    }
}
