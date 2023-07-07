package com.example.clonemoneylover.database;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.clonemoneylover.ServiceHandler;
import com.example.clonemoneylover.TransactionModel;

public class DBService extends Service {
    private static final String TAG = "DBService";

//    public value
    public static final String COMMAND_ACTIVITY2SERVICE = "command";
    public static final String ACTION_ACTIVITY2SERVICE = "action";

    public static final String EXTRA_KEY_GET_USER = "GetUser";
    public static final String EXTRA_KEY_GET_TRANSACTION_LIST = "GetTransList";
    public static final String EXTRA_KEY_GET_TRANSACTION = "GetTrans";
    public static final String EXTRA_KEY_ADD_TRANSACTION = "AddTrans";
    public static final String EXTRA_KEY_EDIT_TRANSACTION = "EditTrans";
    public static final String EXTRA_KEY_DELETE_TRANSACTION = "DeleteTrans";



    private Looper serviceLooper;
    private ServiceHandler serviceHandler;
    private String commandFromActivity;
    private DBManager mDBManager = null;
    private HandleMessageThread mMessageThread = null;
    public DBService() {

    }



    @Override
    public IBinder onBind(Intent intent)
    {
        Toast.makeText(this, "DBService bound", Toast.LENGTH_SHORT).show();

        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate()
    {
        Log.d(TAG, "DBService created" );
        mDBManager = DBManager.getInstance();
        mDBManager.init(this);
        mMessageThread = new HandleMessageThread();

        commandFromActivity = "";

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(TAG, "started" );
        commandFromActivity = intent.getExtras().getString(COMMAND_ACTIVITY2SERVICE);
        mMessageThread.start();


//        stopSelf(startId);
        return startId;
    }

    @Override
    public void onDestroy()
    {
        Log.d(TAG, "destroyed" );
        mMessageThread.cancel();
    }

//    private ArrayList<TransactionModel> GetAllTransactions() {
//        mTransList = curDB.GetAllTransactions();
//        return mTransList;
//    }



//    private UserModel GetUser() {
//        mUser = curDB.GetUser();
//        return mUser;
//    }


    private class HandleMessageThread extends Thread {
        private boolean isRunning = false;
        public HandleMessageThread() {
            Log.i(TAG, "ReadThread Created");
            setName("HandleMessageThread");
        }
        public void cancel() {
            isRunning = false;
        }
        public void run() {
            isRunning = true;
            try {
                while(isRunning) {
                    handleMessage();
                }
            } catch (Exception e) {
                Log.e(TAG, "Exception when handle command from Activity" + e);
            }
        }
        private void handleMessage() {
            switch (commandFromActivity) {
                case EXTRA_KEY_GET_TRANSACTION_LIST: {
                    Intent broadCastIntent = new Intent(ACTION_ACTIVITY2SERVICE);
//                GetAllTransactions();
                    broadCastIntent.putExtra(EXTRA_KEY_GET_TRANSACTION_LIST, mTransList);
                    sendBroadcast(broadCastIntent);
                    break;
                }

                case EXTRA_KEY_GET_TRANSACTION: {
                    Intent broadCastIntent = new Intent(ACTION_ACTIVITY2SERVICE);
                    GetTransaction();
                    broadCastIntent.putExtra(EXTRA_KEY_GET_TRANSACTION, mTrans);
                    sendBroadcast(broadCastIntent);
                    break;
                }

//            case EXTRA_KEY_GET_USER: {
//                Intent broadCastIntent = new Intent(ACTION_ACTIVITY2SERVICE);
//                GetTransaction();
//                broadCastIntent.putExtra(EXTRA_KEY_GET_USER, mTrans);
//                sendBroadcast(broadCastIntent);
//                break;
//            }

                default: {
//                Intent broadCastIntent = new Intent("abc");
//                broadCastIntent.putExtra("db", "do nothing");
//                sendBroadcast(broadCastIntent);
                    break;
                }
            }
        }

    }
}