package mobile.divulga.editais.ifsuldeminas.edu.br.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import mobile.divulga.editais.ifsuldeminas.edu.br.R;
import mobile.divulga.editais.ifsuldeminas.edu.br.activity.ActivityIndex;
import mobile.divulga.editais.ifsuldeminas.edu.br.other.Session;

public class MessageHandler extends FirebaseMessagingService {
    private static final int REQUEST_CODE = 1;
    private static final int NOTIFICATION_ID = 6578;

    public MessageHandler() {
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        final String title = remoteMessage.getNotification().getTitle();
        final String body = remoteMessage.getNotification().getBody();
        final int noticeId = Integer.parseInt(remoteMessage.getData().get("notice"));
        showNotifications(title, body, noticeId);

        Session session = new Session(this);
        session.setNoticeId(noticeId);
    }

    private void showNotifications(String title, String body, int noticeId) {
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("default",
                    "DivulgaEditais",
                    NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription("Edital Divulgado");
            mNotificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), "default")
                .setSmallIcon(R.mipmap.ic_launcher) // notification icon
                .setContentTitle(title) // title for notification
                .setContentText(body)// message for notification
                //.setSound(alarmSound) // set alarm sound for notification
                .setAutoCancel(true); // clear notification after click
        Intent intent = new Intent(getApplicationContext(), ActivityIndex.class);
        intent.putExtra("Notice", noticeId);

        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pi);
        mNotificationManager.notify(0, mBuilder.build());
    }
}