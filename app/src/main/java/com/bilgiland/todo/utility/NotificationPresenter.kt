package com.bilgiland.todo.utility

import android.view.View
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.databinding.BindingAdapter
import com.bilgiland.todo.R
import com.bilgiland.todo.utility.ConstValue.CHANNEL_ID

/**
 * show notification by click onn task
 */
object NotificationPresenter {
    @JvmStatic
    @BindingAdapter("app:longClick")
    fun presenterNotification(view: View, text: String) {
        view.setOnLongClickListener {

            //get context from view
            val context = it.context.applicationContext


            val remoteView = RemoteViews(context.packageName, R.layout.notification_layout)
            remoteView.setTextViewText(R.id.tv_notification, text)
            remoteView.setImageViewResource(R.id.img_notification,R.mipmap.ic_launcher)


            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setCustomContentView(remoteView)
                .setSmallIcon(R.mipmap.ic_launcher)


            NotificationManagerCompat.from(context).apply {
                notify(1, builder.build())
            }

            return@setOnLongClickListener false
        }
    }
}
