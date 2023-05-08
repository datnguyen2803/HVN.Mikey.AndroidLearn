package com.example.clonemoneylover;

import android.app.Service;
import android.content.Intent;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;
import android.os.Process;

import java.util.ArrayList;

public class DBService extends Service {

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
    private DBHandler curDB;
    private UserModel curUser;
    private WalletModel curWallet;
    private ArrayList<TransactionModel> curTransList;
    private TransactionModel curTrans;

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
        Toast.makeText(this, "DBService created", Toast.LENGTH_SHORT).show();
        HandlerThread thread = new HandlerThread("ServiceStartArguments", Process.THREAD_PRIORITY_BACKGROUND);
        thread.start();
        serviceLooper = thread.getLooper();
        serviceHandler = new ServiceHandler(serviceLooper);
        serviceHandler.mainJob = this::HandleCommand;

        curDB = new DBHandler(this);
        curUser = new UserModel();
        curWallet = new WalletModel();
        curTransList = new ArrayList<>();
        curTrans = new TransactionModel();
        commandFromActivity = "";

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Toast.makeText(this, "DBService  Started", Toast.LENGTH_SHORT).show();
        commandFromActivity = intent.getExtras().getString(COMMAND_ACTIVITY2SERVICE);

        Message msg = serviceHandler.obtainMessage();
        serviceHandler.sendMessage(msg);

        stopSelf(startId);
        return startId;
    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "DBService stopped", Toast.LENGTH_SHORT).show();
    }

    private ArrayList<TransactionModel> GetAllTransactions() {
        curTransList = curDB.GetAllTransactions();
        return curTransList;
    }

    private TransactionModel GetTransaction() {
        curTrans = curDB.GetTransactionById(1);
        return curTrans;
    }

//    private UserModel GetUser() {
//        curUser = curDB.GetUser();
//        return curUser;
//    }

    public void HandleCommand() {
        switch (commandFromActivity) {
            case EXTRA_KEY_GET_TRANSACTION_LIST: {
                Intent broadCastIntent = new Intent(ACTION_ACTIVITY2SERVICE);
                GetAllTransactions();
                broadCastIntent.putExtra(EXTRA_KEY_GET_TRANSACTION_LIST, curTransList);
                sendBroadcast(broadCastIntent);
                break;
            }

            case EXTRA_KEY_GET_TRANSACTION: {
                Intent broadCastIntent = new Intent(ACTION_ACTIVITY2SERVICE);
                GetTransaction();
                broadCastIntent.putExtra(EXTRA_KEY_GET_TRANSACTION, curTrans);
                sendBroadcast(broadCastIntent);
                break;
            }

//            case EXTRA_KEY_GET_USER: {
//                Intent broadCastIntent = new Intent(ACTION_ACTIVITY2SERVICE);
//                GetTransaction();
//                broadCastIntent.putExtra(EXTRA_KEY_GET_USER, curTrans);
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