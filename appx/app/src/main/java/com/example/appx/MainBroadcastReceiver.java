package com.example.appx;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class MainBroadcastReceiver extends BroadcastReceiver {

    String TAG = "[From Broadcast]: ";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "Intent Detected.", Toast.LENGTH_LONG).show();
        String actionName = intent.getAction();
        Log.d(TAG, "Intent " + actionName + " come");
    }
}
