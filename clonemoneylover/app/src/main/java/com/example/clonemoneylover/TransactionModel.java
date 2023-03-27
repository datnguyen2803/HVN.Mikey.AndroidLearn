package com.example.clonemoneylover;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class TransactionModel {

    public int getMoney() {
        return mMoney;
    }

    public void setMoney(int mMoney) {
        this.mMoney = mMoney;
    }

    public TransactionType getTransType() {
        return mTransType;
    }

    public void setTransType(TransactionType mTransType) {
        this.mTransType = mTransType;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public LocalDate getDate() {
        return mDate;
    }

    public void setDate(LocalDate mDate) {
        this.mDate = mDate;
    }

    public LocalTime getTime() {
        return mTime;
    }

    public void setTime(LocalTime mTime) {
        this.mTime = mTime;
    }

    private int mMoney;
    private TransactionType mTransType;
    private String mDescription;
    private LocalDate mDate;
    private LocalTime mTime;

//    public TransactionModel()
//    {
//        mMoney = 0;
//        mTransType = new TransactionType();
//        mDescription = "";
//        mDate = LocalDate.now();
//        mTime = LocalTime.now();
//    }

    public TransactionModel(int _money, TransactionType _transType, String _description) {
        this.mMoney = _money;
        this.mTransType = _transType;
        this.mDescription = _description;
        mDate = LocalDate.now();
        mTime = LocalTime.now();
    }

    public static ArrayList<TransactionModel> loadFromDB(int numTrans)
    {
        ArrayList<TransactionModel> transList = new ArrayList<TransactionModel>();

        for (int i = 1; i <= numTrans; i++)
        {
            TransactionType transType = new TransactionType(CommonValue.TransactionMajorType.eTRANSFER_MONEY_INCOMING, CommonValue.TransactionMinorType.eTRANSFER_IN_SALARY);
            transList.add(new TransactionModel(i*1000, transType, "trans from" + i));
        }

        return transList;
    }



}
