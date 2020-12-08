package com.wims.whereismystore.Class;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class MyFcmService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d(TAG,"OnMessageReceived ID: "+remoteMessage.getMessageId());
        Log.d(TAG,"OnMessageReceived DATA: "+remoteMessage.getData());
    }
}
