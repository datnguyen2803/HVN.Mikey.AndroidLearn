package com.example.clonemoneylover;


import static com.example.clonemoneylover.database.DBHandler.UUID_EMPTY;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class TransactionModel implements Serializable {
    private UUID mUiId;
    private int mMoney;
    private TransactionType mTransType;
    private String mDescription;
    private LocalDate mDate;
    private LocalTime mTime;

    public UUID getUiId() {
        return mUiId;
    }
    public void setUiId(UUID uiId) {
        this.mUiId = uiId;
    }
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

    public TransactionModel() {
        this.mUiId = UUID_EMPTY;
        this.mMoney = 0;
        this.mTransType = new TransactionType(CommonValue.TransactionMinorType.eTRANSFER_OUT_NONE);
        this.mDescription = "";
        mDate = LocalDate.now();
        mTime = LocalTime.now();
    }

    public TransactionModel(int money, CommonValue.TransactionMinorType minorType, String description) {
        this.mUiId = UUID.randomUUID();
        this.mMoney = money;
        this.mTransType = new TransactionType(minorType);
        this.mDescription = description;
        mDate = LocalDate.now();
        mTime = LocalTime.now();
    }

    public TransactionModel(int money, CommonValue.TransactionMinorType minorType, String description, LocalDate date, LocalTime time) {
        this.mUiId = UUID.randomUUID();
        this.mMoney = money;
        this.mTransType = new TransactionType(minorType);
        this.mDescription = description;
        this.mDate = date;
        this.mTime = time;
    }
    public TransactionModel(UUID uiId, int money, CommonValue.TransactionMinorType minorType, String description, LocalDate date, LocalTime time) {
        this.mUiId = uiId;
        this.mMoney = money;
        this.mTransType = new TransactionType(minorType);
        this.mDescription = description;
        this.mDate = date;
        this.mTime = time;
    }

}
