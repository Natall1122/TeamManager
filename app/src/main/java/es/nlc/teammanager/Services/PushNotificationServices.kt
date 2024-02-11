package es.nlc.teammanager.Services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import es.nlc.teammanager.MainActivity
import es.nlc.teammanager.R

class PushNotificationServices : FirebaseMessagingService() {
    companion object {
        const val CHANNEL_ID = "FCM CID"
        const val CHANNEL_NAME = "FCM notification channel"
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)


        if(message.notification != null){
            val title = message.notification!!.title
            val body = message.notification!!.body

            Log.d(
                "notificacio","Notification with title $title and body $body"
            )

            val intent = Intent(this, MainActivity::class.java).apply{
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            val pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_IMMUTABLE
            )

            val notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)

            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH
                )
                manager.createNotificationChannel(channel)
            }
            manager.notify(1,notificationBuilder.build())
        }
    }
}