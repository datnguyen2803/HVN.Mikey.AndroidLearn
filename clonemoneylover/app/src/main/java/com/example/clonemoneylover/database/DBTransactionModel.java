package com.example.clonemoneylover.database;

import static com.example.clonemoneylover.database.DBHandler.UUID_EMPTY;

import com.example.clonemoneylover.CommonValue;
import com.example.clonemoneylover.TransactionModel;
import com.example.clonemoneylover.WalletModel;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public class DBTransactionModel {
    private UUID mId;
    private UUID mUiId; // ID for ui component, used to link TransactionModel and DBTransactionModel
    private UUID mWalletId;
    private int mMoney;
    private CommonValue.TransactionMinorType mType;
    private LocalDate mDate;
    private LocalTime mTime;
    private String mDescription;

    public UUID getId() {
        return mId;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public UUID getUiId() {
        return mUiId;
    }
    public void setUiId(UUID uiId) {
        this.mUiId = uiId;
    }
    public UUID getWalletId() {
        return mWalletId;
    }
    public void setWalletId(UUID walletId) {
        this.mWalletId = walletId;
    }
    public int getMoney() {
        return mMoney;
    }
    public void setMoney(int money) {
        this.mMoney = money;
    }
    public CommonValue.TransactionMinorType getType() {
        return mType;
    }
    public void setType(CommonValue.TransactionMinorType type) {
        this.mType = type;
    }
    public LocalDate getDate() {
        return mDate;
    }
    public void setDate(LocalDate date) {
        this.mDate = mDate;
    }
    public LocalTime getTime() {
        return mTime;
    }
    public void setTime(LocalTime time) {
        this.mTime = time;
    }
    public String getDescription() {
        return mDescription;
    }
    public void setDescription(String description) {
        this.mDescription = description;
    }



    public DBTransactionModel() {
        this.mId = UUID_EMPTY;
        this.mUiId = UUID_EMPTY;
        this.mWalletId = UUID_EMPTY;
        this.mMoney = 0;
        this.mType = CommonValue.TransactionMinorType.eTRANSFER_OUT_NONE;
        this.mDate = LocalDate.now();
        this.mTime = LocalTime.now();
        this.mDescription = null;
    }

    public DBTransactionModel(int money, CommonValue.TransactionMinorType type, LocalDate date, LocalTime time, String description) {
        this.mId = UUID_EMPTY;
        this.mUiId = UUID_EMPTY;
        this.mWalletId = UUID_EMPTY;
        this.mMoney = money;
        this.mType = type;
        this.mDate = date;
        this.mTime = time;
        this.mDescription = description;
    }

    public DBTransactionModel(UUID id, UUID uiId, UUID walletId, int money, CommonValue.TransactionMinorType type, LocalDate date, LocalTime time, String description) {
        this.mId = id;
        this.mUiId = uiId;
        this.mWalletId = walletId;
        this.mMoney = money;
        this.mType = type;
        this.mDate = date;
        this.mTime = time;
        this.mDescription = description;
    }

    public TransactionModel ToTransactionModel() {
        return new TransactionModel(mMoney, mType, mDescription, mDate, mTime);
    }
}
