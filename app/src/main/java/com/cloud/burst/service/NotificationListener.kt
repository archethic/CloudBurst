/**
 * Created by naijun on 2021/10/24
 * Copyright (c) naijun.
 * This code is licensed under the MIT Licensing Principles.
 */

package com.cloud.burst.service

import android.app.Notification
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.cloud.burst.setting.Constants
import java.util.*

class NotificationListener : NotificationListenerService() {
    override fun onCreate() {
        super.onCreate()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onNotificationPosted(sbn: StatusBarNotification?) {
        super.onNotificationPosted(sbn)
        if(sbn === null || Constants.allowPackageNames.find { sbn.packageName.startsWith(it) }.isNullOrEmpty()) return
        try {
            val extender: Notification.WearableExtender = Notification.WearableExtender(sbn.notification)
            for (action in extender.actions) {
                if (action.remoteInputs.isEmpty()) return
                if (action.title.toString().lowercase(Locale.getDefault()).contains("reply") ||
                        action.title.toString().lowercase(Locale.getDefault()).contains("답장")) {
                    val data = sbn.notification.extras

                    println("[ RECEIVED ] ${data.toString()}") //DEBUG
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(applicationContext, e.toString(), Toast.LENGTH_LONG).show()
        }
    }
}