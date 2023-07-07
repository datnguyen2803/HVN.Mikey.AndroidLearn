package com.example.clonemoneylover;

import static com.example.clonemoneylover.CommonValue.WalletType;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

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

import com.example.clonemoneylover.database.DBService;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentTransactions#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentTransactions extends Fragment {

    private static final String TAG = "FragmentTransaction";
    View mView;
    Context mContext;
    BroadcastReceiver mServiceReceiver;
    UserModel mUser;
    WalletModel mMainWallet;
    ArrayList<TransactionModel> mTransList;
    TransactionModel mTrans;

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

        Init();
        LoadFromDB();
        LoadToView();

        return mView;
    }

    private void Init() {
        mUser = new UserModel();
        mMainWallet = new WalletModel();
        mTransList = new ArrayList<>();
        mTrans = new TransactionModel();
        mServiceReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //Extract your data - better to use constants for keys
                String action = intent.getAction();
                Log.d(TAG, action);
                Bundle extras = intent.getExtras();
                if(extras == null) {
                    return;
                }
                for (String extraKey : extras.keySet()) {
                    Log.d(TAG, extraKey);
                    switch (extraKey) {
                        case DBService.EXTRA_KEY_GET_TRANSACTION_LIST: {
                            mTransList = (ArrayList<TransactionModel>) intent.getSerializableExtra(extraKey);
                            break;
                        }

                        case DBService.EXTRA_KEY_GET_TRANSACTION: {
                            mTrans = (TransactionModel) intent.getSerializableExtra(extraKey);
                            break;
                        }

                        case DBService.EXTRA_KEY_GET_USER: {
                            mUser = (UserModel) intent.getSerializableExtra(extraKey);
                        }

                        default: {
                            break;
                        }
                    }
                }
                LoadFromDB();
                LoadToView();
                mView.invalidate();
            }
        };
        mContext.registerReceiver(mServiceReceiver, new IntentFilter(DBService.ACTION_ACTIVITY2SERVICE));
        Intent intent = new Intent(mContext, DBService.class);
        intent.putExtra(DBService.COMMAND_ACTIVITY2SERVICE, DBService.EXTRA_KEY_GET_TRANSACTION_LIST);
        mContext.startService(intent);

    }

    private void LoadFromDB()
    {

        // Get data
        ArrayList<TransactionModel> tempPayTransList = mTransList;
        WalletModel tempPayWallet = new WalletModel(tempPayTransList, WalletType.eWALLET_TYPE_PAY, "Pay wallet");
//        ArrayList<TransactionModel> tempSavingTransList = TransactionModel.loadFromDB(4);
//        WalletModel tempSavingWallet = new WalletModel(tempSavingTransList, WalletType.eWALLET_TYPE_SAVING, "Saving wallet");
        ArrayList<WalletModel> tempWalletList = new ArrayList<WalletModel>() {
            {
                add(tempPayWallet);
//                add(tempSavingWallet);
            }
        };

        mUser.setName("Dat chaos");
        mUser.setWalletList(tempWalletList);
        mMainWallet = mUser.getWalletList().get(0);

//        drop down items
        ArrayList<String> dropdownWalletItemList = new ArrayList<String>() {
            {
                add(mUser.getWalletList().get(0).getDescription());
//                add(mUser.getWalletList().get(1).getDescription());
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