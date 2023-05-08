package com.example.clonemoneylover;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener{

    String TAG = "[MIKEY] ";

    BroadcastReceiver mServiceReceiver;
    BottomNavigationView bottomNavigationView;
    FragmentHome fragmentHome = new FragmentHome();
    FragmentTransactions fragmentTransactions = new FragmentTransactions();
    UserModel curUser = new UserModel();
    WalletModel curWallet = new WalletModel();
    ArrayList<TransactionModel> curTransList = new ArrayList<>();
    TransactionModel curTrans = new TransactionModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() event");

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_home);
        mServiceReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Extract your data - better to use constants for keys
                String extraKey = intent.getAction();
                switch (extraKey) {
                    case DBService.EXTRA_KEY_GET_TRANSACTION_LIST:
                    {
                        curTransList = (ArrayList<TransactionModel>) intent.getSerializableExtra(extraKey);
                        break;
                    }

                    case DBService.EXTRA_KEY_GET_TRANSACTION:
                    {
                        curTrans = (TransactionModel) intent.getSerializableExtra(extraKey);
                        break;
                    }

                    case DBService.EXTRA_KEY_GET_USER:
                    {
                        curUser = (UserModel) intent.getSerializableExtra(extraKey);
                    }

                    default:
                    {
                        break;
                    }
                }

            }
        };
    }

    public void onClickButtonLogin(View view)
    {
        String UserName = ((EditText)findViewById(R.id.editTextUsername)).getText().toString();
        String UserPassword = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();

        if(UserName.equals("admin") && UserPassword.equals("admin"))
        {
            Log.d(TAG, "Login success");
        }
        else
        {
            Log.d(TAG, "fail");
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.page_home: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentHome).commit();

//                Intent intent = new Intent(this, DBService.class);
//                intent.putExtra(DBService.COMMAND_ACTIVITY2SERVICE, DBService.EXTRA_KEY_GET_USER);
//                startService(intent);
//                registerReceiver(mServiceReceiver, new IntentFilter(DBService.ACTION_ACTIVITY2SERVICE));
                return true;
            }

            case R.id.page_transactions: {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentTransactions).commit();

//                Intent intent = new Intent(this, DBService.class);
//                intent.putExtra(DBService.COMMAND_ACTIVITY2SERVICE, DBService.EXTRA_KEY_GET_TRANSACTION_LIST);
//                startService(intent);
//                registerReceiver(mServiceReceiver, new IntentFilter(DBService.ACTION_ACTIVITY2SERVICE));
                return true;
            }

            case R.id.page_quick_add_transaction: {
                Intent intent = new Intent(MainActivity.this, AddTransactionActivity.class);
                startActivity(intent);
//                unregisterReceiver(mServiceReceiver);
                return true;
            }

            default:
                break;
        }
        return false;
    }

}