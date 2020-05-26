package com.bilgiland.todo

import android.app.Application
import com.bilgiland.todo.data.db.TodoDb
import com.bilgiland.todo.data.repository.TodoRepo
import com.bilgiland.todo.ui.MainViewModel
import com.bilgiland.todo.ui.MainViewModelFactory
import com.bilgiland.todo.ui.TodoAdapter
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind() from singleton { TodoDb(instance()) }
        bind() from singleton { TodoRepo(instance()) }
        bind() from singleton { MainViewModel(instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
        bind() from provider { TodoAdapter() }
    }
}