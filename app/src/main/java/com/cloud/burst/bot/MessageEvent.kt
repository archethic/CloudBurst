/**
 * Created by naijun on 2021/10/24
 * Copyright (c) naijun.
 * This code is licensed under the MIT Licensing Principles.
 */

package com.cloud.burst.bot

import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.eclipsesource.v8.V8
import com.eclipsesource.v8.V8Object

class MessageEvent(val bundle: Bundle) {
    fun getV8Object(v8: V8): V8Object {
        val senderName = bundle.getString(NotificationCompat.EXTRA_TITLE)
        val roomName = bundle.getString(NotificationCompat.EXTRA_SUMMARY_TEXT)
        return V8Object(v8).add("message", bundle.getString(NotificationCompat.EXTRA_TEXT))
            .add("sender", V8Object(v8).add("name", senderName))
            .add("room", V8Object(v8).add("name", roomName ?: senderName).add("isGroupChat", roomName != null))
    }
}