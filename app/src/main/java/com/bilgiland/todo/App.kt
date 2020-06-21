package com.bilgiland.todo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.bilgiland.todo.data.db.TodoDb
import com.bilgiland.todo.data.repository.TodoRepo
import com.bilgiland.todo.ui.main.*
import com.bilgiland.todo.utility.ConstValue.CHANNEL_ID
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class App : Application(), KodeinAware {
    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Create the NotificationChannel
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_LOW
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))
        bind() from singleton { TodoDb(instance()) }
        bind() from singleton { TodoRepo(instance()) }
        bind() from singleton { MainViewModel(instance()) }
        bind() from provider { MainViewModelFactory(instance()) }
        bind() from provider { TodoAdapter(context as AdapterListener) }
    }


}