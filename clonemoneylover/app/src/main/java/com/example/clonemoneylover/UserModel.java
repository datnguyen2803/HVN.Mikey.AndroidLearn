package com.example.clonemoneylover;

import java.util.ArrayList;

public class UserModel {
    ArrayList<WalletModel> mWalletList;
    int mTotalBalance;
    String mName;

    /**
     * GETTER and SETTER
     */
    public ArrayList<WalletModel> getWalletList() {
        return mWalletList;
    }

    public void setWalletList(ArrayList<WalletModel> mWalletList) {
        this.mWalletList = mWalletList;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getTotalBalance() {
        return mTotalBalance;
    }


    /**
     * CONSTRUCTORS
     */
    public UserModel() {
        mWalletList = new ArrayList<WalletModel>();
        mTotalBalance = 0;
        mName = "";
    }


    public UserModel(ArrayList<WalletModel> mWalletList, String name) {
        this.mWalletList = mWalletList;
        mName = name;

        for(WalletModel wallet : mWalletList)
        {
            mTotalBalance += wallet.getCurrentBalance();
        }
    }
}
