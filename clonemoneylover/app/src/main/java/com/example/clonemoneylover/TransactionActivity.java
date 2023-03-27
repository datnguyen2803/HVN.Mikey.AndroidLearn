package com.example.clonemoneylover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class TransactionActivity extends AppCompatActivity {


    ArrayList<TransactionModel> myTransList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        RecyclerView rvTransactionList = (RecyclerView) findViewById(R.id.rvTransactionList);

        // Initialize contacts
        myTransList = TransactionModel.loadFromDB(10);
        // Create adapter passing in the sample user data
        TransactionAdapter adapter = new TransactionAdapter(myTransList);
        // Attach the adapter to the recyclerview to populate items
        rvTransactionList.setAdapter(adapter);
        // Set layout manager to position the items
        rvTransactionList.setLayoutManager(new LinearLayoutManager(this));
    }

}