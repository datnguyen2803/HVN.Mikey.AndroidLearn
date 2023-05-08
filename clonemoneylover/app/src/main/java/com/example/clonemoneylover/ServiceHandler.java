package com.example.clonemoneylover;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

interface ServiceInterface {
    void Execute();
}
public class ServiceHandler extends Handler {
    public ServiceInterface mainJob;
    public ServiceHandler(Looper looper) {
        super(looper);
        mainJob = () -> Log.d("aksjdhasd", "datchaos");
    }
    @Override
    public void handleMessage(Message msg) {
        // Normally we would do some work here, like download a file.
        // For our sample, we just sleep for 5 seconds.
        try {
            Thread.sleep(10);
            this.mainJob.Execute();
        } catch (InterruptedException e) {
            // Restore interrupt status.
            Thread.currentThread().interrupt();
        }
        // Stop the service using the startId, so that we don't stop
        // the service in the middle of handling another job
//        call stopSelf in Service
//        stopSelf(msg.arg1);
    }
}
