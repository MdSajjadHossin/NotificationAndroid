 package com.example.notificationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    public static  final  String CHANNEL_ID = "Message Channel";
    public static final int NOTIFICATION_ID = 100;
    public static final int REQ_CODE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Drawable drawable = ResourcesCompat.getDrawable(getResources(),R.drawable.system,null);
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        Bitmap largeIcon = bitmapDrawable.getBitmap();

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        Notification notification;
        Intent inotify = new Intent(getApplicationContext(), MainActivity.class);
        inotify.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pI = PendingIntent.getActivity(this, REQ_CODE, inotify,PendingIntent.FLAG_UPDATE_CURRENT);

        //Big Picture Style
        Notification.BigPictureStyle bigPictureStyle = new Notification.BigPictureStyle()
                .bigPicture(((BitmapDrawable) (ResourcesCompat.getDrawable(getResources(),R.drawable.system,null))).getBitmap())
                .bigLargeIcon(largeIcon)
                .setBigContentTitle("Image Sent By Sajid")
                .setSummaryText("Media Message");


        //Inbox Style
        Notification.InboxStyle inboxStyle = new Notification.InboxStyle()
                .addLine("A")
                .addLine("B")
                .addLine("C")
                .addLine("D")
                .addLine("E")
                .addLine("F")
                .addLine("G")
                .addLine("H")
                .addLine("I")
                .addLine("J")
                .addLine("K")
                .addLine("L")
                .setBigContentTitle("Complete Message")
                .setSummaryText("Message From Sajid");


        //Creating Notification
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.system)
                    .setContentText("New Message")
                    .setSubText("New Message From Rahul")
                    .setContentIntent(pI)
                    .setStyle(inboxStyle)
                    .setAutoCancel(false)
                    .setChannelId(CHANNEL_ID)
                    .build();
            notificationManager.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "New Channel", NotificationManager.IMPORTANCE_HIGH));
        }else{
            notification = new Notification.Builder(this)
                    .setLargeIcon(largeIcon)
                    .setSmallIcon(R.drawable.system)
                    .setContentText("New Message")
                    .setSubText("New Message From Paplu")
                    .setContentIntent(pI)
                    .setAutoCancel(false)
                    .setStyle(inboxStyle)
                    .build();

        }
        notificationManager.notify(NOTIFICATION_ID, notification);

    }

}