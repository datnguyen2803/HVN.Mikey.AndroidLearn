package com.example.clonemoneylover;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTransactions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTransactions extends Fragment {

    View mView;
    Context mContext;

    static ArrayList<TransactionModel> myTransList;
    public FragmentTransactions() {
        // Required empty public constructor
    }

    public static FragmentTransactions newInstance(String param1, String param2) {
        FragmentTransactions fragment = new FragmentTransactions();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_transactions, container, false);
        mContext = getActivity();
        CreateTransList();

        return mView;
    }

    private void CreateTransList()
    {
        RecyclerView rvTransactionList = (RecyclerView) mView.findViewById(R.id.rvTransactionList);
        // Initialize contacts
        myTransList = TransactionModel.loadFromDB(10);
        // Create adapter passing in the sample user data
        TransactionAdapter adapter = new TransactionAdapter(myTransList);
        // Attach the adapter to the recyclerview to populate items
        rvTransactionList.setAdapter(adapter);
        // Set layout manager to position the items
        rvTransactionList.setLayoutManager(new LinearLayoutManager(mContext));
    }
}