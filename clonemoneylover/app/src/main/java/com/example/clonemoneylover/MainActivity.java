package com.example.clonemoneylover;

import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener{

    String msg = "[MIKEY] ";

    BottomNavigationView bottomNavigationView;
    FragmentHome fragmentHome = new FragmentHome();
    FragmentTransactions fragmentTransactions = new FragmentTransactions();

//    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(msg, "onCreate() event");

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.page_home);
    }

    public void onClickButtonLogin(View view)
    {
        String UserName = ((EditText)findViewById(R.id.editTextUsername)).getText().toString();
        String UserPassword = ((EditText)findViewById(R.id.editTextPassword)).getText().toString();

        if(UserName.equals("admin") && UserPassword.equals("admin"))
        {
            Log.d(msg, "Login success");
        }
        else
        {
            Log.d(msg, "fail");
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.page_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentHome).commit();
                return true;

            case R.id.page_transactions:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_content, fragmentTransactions).commit();
                return true;
            default:
                break;
        }
        return false;
    }

}