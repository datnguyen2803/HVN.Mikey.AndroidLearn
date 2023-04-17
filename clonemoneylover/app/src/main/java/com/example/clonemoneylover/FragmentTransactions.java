package com.example.clonemoneylover;

import static com.example.clonemoneylover.CommonValue.WalletType;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTransactions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTransactions extends Fragment {

    View mView;
    Context mContext;
    UserModel mUser;
    WalletModel mMainWallet;

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

        LoadFromDB();
        LoadToView();


        return mView;
    }

    private void LoadFromDB()
    {
        // Get data
        ArrayList<TransactionModel> tempPayTransList = TransactionModel.loadFromDB(10);
        WalletModel tempPayWallet = new WalletModel(tempPayTransList, WalletType.eWALLET_TYPE_PAY, "Pay wallet");
        ArrayList<TransactionModel> tempSavingTransList = TransactionModel.loadFromDB(4);
        WalletModel tempSavingWallet = new WalletModel(tempSavingTransList, WalletType.eWALLET_TYPE_SAVING, "Saving wallet");
        ArrayList<WalletModel> tempWalletList = new ArrayList<WalletModel>() {
            {
                add(tempPayWallet);
                add(tempSavingWallet);
            }
        };

        mUser = new UserModel(tempWalletList, "DatChaos");
        mMainWallet = mUser.getWalletList().get(0);

//        drop down items
        ArrayList<String> dropdownWalletItemList = new ArrayList<String>() {
            {
                add(mUser.getWalletList().get(0).getDescription());
                add(mUser.getWalletList().get(1).getDescription());
            }
        };
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(mContext, R.layout.wallet_dropdown_item, dropdownWalletItemList);
        AutoCompleteTextView dropdownWalletItem = (AutoCompleteTextView) mView.findViewById(R.id.dropdownWalletItem);
        dropdownWalletItem.setText(dropdownWalletItemList.get(0));
        dropdownWalletItem.setAdapter(arrayAdapter);
        dropdownWalletItem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("MIKEY", "onItemClick: dropdownmenu");
                String selectedItem = parent.getItemAtPosition(position).toString();
//                dropdownWalletItem.setText(selectedItem);
                // Perform any necessary actions with the selected item
                mMainWallet = mUser.getWalletList().get(position);
                LoadToView();
                view.invalidate();


            }
        });

    }

    private void LoadToView()
    {
        // View TransactionsList
        RecyclerView rvTransactionList = (RecyclerView) mView.findViewById(R.id.rvTransactionList);
        // Create adapter passing in the sample user data
        TransactionAdapter adapter = new TransactionAdapter(mMainWallet.getTransList());
        // Attach the adapter to the recyclerview to populate items
        rvTransactionList.setAdapter(adapter);
        // Set layout manager to position the items
        rvTransactionList.setLayoutManager(new LinearLayoutManager(mContext));

        // View Balance
        TextView tvCurrentBalance = mView.findViewById(R.id.tvCurrentBalance);
        tvCurrentBalance.setText(String.valueOf(mUser.getTotalBalance()));


        TextView tvInflow = mView.findViewById(R.id.tvInflow);
        tvInflow.setText(String.valueOf(mMainWallet.getInflowMoney()));

        TextView tvOutflow = mView.findViewById(R.id.tvOutflow);
        tvOutflow.setText(String.valueOf(mMainWallet.getOutflowMoney()));

        TextView tvTimeBalance = mView.findViewById(R.id.tvTimeBalance);
        tvTimeBalance.setText(String.valueOf(mMainWallet.getCurrentBalance()));

    }

}