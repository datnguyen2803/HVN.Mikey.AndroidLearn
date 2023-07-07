package com.example.clonemoneylover;

import java.util.ArrayList;

public class UserModel {
    int mTotalBalance;
    String mName;
    String mEmail;
    ArrayList<WalletModel> mWalletList;

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
    public void setTotalBalance(int totalBalance) {
        mTotalBalance = totalBalance;
    }
    public String getEmail() {
        return mEmail;
    }
    public void setEmail(String email) {
        this.mEmail = email;
    }

    /**
     * CONSTRUCTORS
     */
    public UserModel() {
        mWalletList = new ArrayList<WalletModel>();
        mTotalBalance = 0;
        mName = "";
    }


    public UserModel(ArrayList<WalletModel> mWalletList, String name, String email) {
        this.mWalletList = mWalletList;
        mName = name;
        mEmail = email;
        for(WalletModel wallet : mWalletList)
        {
            mTotalBalance += wallet.getCurrentBalance();
        }
    }
}
