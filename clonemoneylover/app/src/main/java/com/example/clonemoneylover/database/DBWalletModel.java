package com.example.clonemoneylover.database;

import com.example.clonemoneylover.CommonValue;
import com.example.clonemoneylover.TransactionModel;
import com.example.clonemoneylover.WalletModel;

import java.util.ArrayList;
import java.util.UUID;

public class DBWalletModel {
    public UUID getId() {
        return mId;
    }

    public void setId(UUID id) {
        this.mId = id;
    }

    public UUID getUserId() {
        return mUserId;
    }

    public void setUserId(UUID userId) {
        this.mUserId = userId;
    }

    public int getBalance() {
        return mBalance;
    }

    public void setBalance(int balance) {
        this.mBalance = balance;
    }

    public CommonValue.WalletType getType() {
        return mType;
    }

    public void setType(CommonValue.WalletType type) {
        this.mType = type;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        this.mDescription = description;
    }

    private UUID mId;
    private UUID mUserId;
    private int mBalance;
    private CommonValue.WalletType mType;
    private String mDescription;


    public DBWalletModel() {
        this.mId = new UUID(0L, 0L);
        this.mUserId = new UUID(0L, 0L);
        this.mBalance = 0;
        this.mType = CommonValue.WalletType.eWALLET_TYPE_NONE;
        this.mDescription = null;
    }

    public DBWalletModel(int balance, CommonValue.WalletType type, String description) {
        this.mId = new UUID(0L, 0L);
        this.mUserId = new UUID(0L, 0L);
        this.mBalance = balance;
        this.mType = type;
        this.mDescription = description;
    }

    public DBWalletModel(UUID id, UUID userId, int balance, CommonValue.WalletType type, String description) {
        this.mId = id;
        this.mUserId = userId;
        this.mBalance = balance;
        this.mType = type;
        this.mDescription = description;
    }


}
