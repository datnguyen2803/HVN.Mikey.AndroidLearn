package com.example.appx;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appx.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

//    private ActivityMainBinding binding;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
//                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);
//
//
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.d("testlog", "datchaos");
//            }
//        }).start();
//    }

    String msg = "Android : ";
    String studenProviderURL = "content://com.example.appx.StudentProvider";

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        Log.d(msg, "onCreate() event");
    }

    /** Called when the activity is about to become visible. */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(msg, "onStart() event");
        RegisterBroadcast();
    }

    /** Called when the activity has become visible. */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(msg, "onResume() event");
    }

    /** Called when another activity is taking focus. */
    @Override
    protected void onPause() {
        super.onPause();
        Log.d(msg, "onPause() event");
    }

    /** Called when the activity is no longer visible. */
    @Override
    protected void onStop() {
        super.onStop();
        Log.d(msg, "onStop() event");
    }

    /** Called just before the activity is destroyed. */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(msg, "onDestroy() event");
    }

    public void startService(View view)
    {
        startService(new Intent(getBaseContext(), MainService.class));
    }

    public void stopService(View view)
    {
        stopService(new Intent(getBaseContext(), MainService.class));
    }

    public void BroadcastIntent(View view)
    {
        Intent intent = new Intent();
        intent.setAction("datchaosIntent");
        sendBroadcast(intent);

        Log.d(msg, "Sent intent datchaosIntent");
    }

    // can be called anywhere to register the broadcast receiver
    private void RegisterBroadcast()
    {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("datchaosIntent");
            MainBroadcastReceiver myBR = new MainBroadcastReceiver();

            registerReceiver(myBR, intentFilter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Add new student record
    public void onClickButtonAddStudent(View view)
    {
        ContentValues values = new ContentValues();
        values.put(StudentProvider.NAME, ((EditText)findViewById(R.id.EditTextName)).getText().toString());
        values.put(StudentProvider.GRADE, ((EditText)findViewById(R.id.EditTextGrade)).getText().toString());

        Uri uri = getContentResolver().insert(StudentProvider.CONTENT_URI, values);
        Toast.makeText(getBaseContext(), uri.toString(), Toast.LENGTH_LONG).show();
    }

    // get grade of student
    @SuppressLint("Range")
    public void onClickButtonGetGrade(View view)
    {

        Uri students = Uri.parse(studenProviderURL);

        Cursor c = managedQuery(students, null, null, null, "name");
        if(c.moveToFirst())
        {
            do {
                Toast.makeText(this, c.getString(c.getColumnIndex(StudentProvider._ID)) +
                        ", " + c.getString(c.getColumnIndex(StudentProvider.NAME)) +
                        ", " + c.getString(c.getColumnIndex(StudentProvider.GRADE)),
                        Toast.LENGTH_LONG).show();
            }
            while (c.moveToNext());
        }
    }

    /* TODO: Add method UPDATE and DELETE */
}