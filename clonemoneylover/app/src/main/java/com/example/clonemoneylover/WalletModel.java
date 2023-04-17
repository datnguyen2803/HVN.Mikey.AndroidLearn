package com.example.clonemoneylover;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class WalletModel {
    ArrayList<TransactionModel> mTransList;
    int mCurrentBalance;
    CommonValue.WalletType eWalletType;
    String mDescription;
    int mOutflowMoney;
    int mInflowMoney;


    /**
     * GETTER and SETTER
     */
    public ArrayList<TransactionModel> getTransList() {
        return mTransList;
    }
    public void setTransList(ArrayList<TransactionModel> mTransList) {
        this.mTransList = mTransList;
    }
    public int getCurrentBalance() {
        return mCurrentBalance;
    }
    public void setCurrentBalance(int mCurrentBalance) {
        this.mCurrentBalance = mCurrentBalance;
    }
    public CommonValue.WalletType getWalletType() {
        return eWalletType;
    }

    public void setWalletType(CommonValue.WalletType eWalletType) {
        this.eWalletType = eWalletType;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getOutflowMoney() {
        return mOutflowMoney;
    }

    public void setOutflowMoney(int mOutflowMoney) {
        this.mOutflowMoney = mOutflowMoney;
    }

    public int getInflowMoney() {
        return mInflowMoney;
    }

    public void setInflowMoney(int mInflowMoney) {
        this.mInflowMoney = mInflowMoney;
    }


    /**
     * CONSTRUCTORS
     */
    public WalletModel() {
        mTransList = new ArrayList<TransactionModel>();
        mCurrentBalance = 0;
        eWalletType = CommonValue.WalletType.eWALLET_TYPE_PAY;
        mDescription = "";
        mOutflowMoney = 0;
        mInflowMoney = 0;
    }

    public WalletModel(@NonNull ArrayList<TransactionModel> transList, CommonValue.WalletType eWalletType, String description) {
        this.mTransList = transList;

        for(TransactionModel trans : transList)
        {
//            mCurrentBalance += trans.getMoney();
            if(trans.getTransType().getMajorType() == CommonValue.TransactionMajorType.eTRANSFER_MONEY_OUTGOING)
            {
                this.mOutflowMoney += trans.getMoney();
            }
            else if(trans.getTransType().getMajorType() == CommonValue.TransactionMajorType.eTRANSFER_MONEY_INCOMING)
            {
                this.mInflowMoney += trans.getMoney();
            }
            else
            {
//                do nothing
            }
            mCurrentBalance = mInflowMoney - mOutflowMoney;
        }
        this.eWalletType = eWalletType;
        this.mDescription = description;

    }


}
