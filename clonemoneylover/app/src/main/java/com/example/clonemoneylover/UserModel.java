package com.example.clonemoneylover;

import java.util.ArrayList;

public class UserModel {
    ArrayList<WalletModel> mWalletList;
    int mTotalBalance;
    String Name;

    public UserModel() {
        mWalletList = new ArrayList<WalletModel>();
        mTotalBalance = 0;
    }


    public UserModel(ArrayList<WalletModel> mWalletList, String name) {
        this.mWalletList = mWalletList;
        Name = name;

        for(WalletModel wallet : mWalletList)
        {
            mTotalBalance += wallet.getCurrentBalance();
        }
    }
}
