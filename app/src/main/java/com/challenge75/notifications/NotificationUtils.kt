package com.challenge75.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

object NotificationUtils {
    private const val CHANNEL_ID = "challenge75_reminders"
    private const val CHANNEL_NAME = "Challenge75 Reminders"
    private const val NOTIF_ID = 1001

    fun createNotificationChannel(context: Context) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (manager.getNotificationChannel(CHANNEL_ID) == null) {
            val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Daily reminders to complete your 75-Day Soft tasks"
            manager.createNotificationChannel(channel)
        }
    }

    fun showReminderNotification(context: Context) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("75-Day Soft")
            .setContentText("Come on Sampii! You got this!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIF_ID, builder.build())
        }
    }
}
