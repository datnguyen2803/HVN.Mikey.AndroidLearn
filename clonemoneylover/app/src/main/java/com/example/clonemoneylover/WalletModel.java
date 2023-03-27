package com.example.clonemoneylover;

import java.util.ArrayList;

public class WalletModel {
    ArrayList<TransactionModel> mTransList;

    public int getCurrentBalance() {
        return mCurrentBalance;
    }

    public void setCurrentBalance(int mCurrentBalance) {
        this.mCurrentBalance = mCurrentBalance;
    }

    int mCurrentBalance;

    public WalletModel() {
        mTransList = new ArrayList<TransactionModel>();
        mCurrentBalance = 0;
    }

    public WalletModel(ArrayList<TransactionModel> transList, int currentBalance) {
        this.mTransList = transList;
        this.mCurrentBalance = currentBalance;
    }


}
