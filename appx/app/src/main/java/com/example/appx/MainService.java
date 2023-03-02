package com.example.appx;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MainService extends Service {

//    @Override
//    public IBinder onBind(Intent intent) {
//        // TODO: Return the communication channel to the service.
//        throw new UnsupportedOperationException("Not yet implemented");
//    }

    String TAG;

    /** indicates how to behave if the service is killed */
    int mStartMode;

    /** interface for clients that bind */
    IBinder mBinder;

    /** indicates whether onRebind should be used */
    boolean mAllowRebind;

    public MainService()
    {
        mStartMode = 0;
        mBinder = null;
        mAllowRebind = false;
        TAG = "CHAOS Service: ";
    }

    /** Called when the service is being created. */
    @Override
    public void onCreate()
    {
        Log.d(TAG, "onCreate() event");
    }

    /** The service is starting, due to a call to startService() */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(TAG, "onStart() event");
        Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
//        return mStartMode;
        return START_STICKY;
    }

    /** A client is binding to the service with bindService() */
    @Nullable
    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(TAG, "onBind() event");
        return mBinder;
    }

    /** Called when all clients have unbound with unbindService() */
    @Override
    public boolean onUnbind(Intent intent)
    {
        Log.d(TAG, "onUnbind() event");
        return mAllowRebind;
    }

    /** Called when a client is binding to the service with bindService()*/
    @Override
    public void onRebind(Intent intent)
    {
        Log.d(TAG, "onRebind() event");
    }

    /** Called when The service is no longer used and is being destroyed */
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.d(TAG, "onDestroy() event");
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }


}