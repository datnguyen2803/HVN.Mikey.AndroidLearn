package com.example.clonemoneylover.database;

import android.content.Context;

import com.example.clonemoneylover.CommonValue;
import com.example.clonemoneylover.TransactionModel;
import com.example.clonemoneylover.UserModel;
import com.example.clonemoneylover.WalletModel;

import java.util.ArrayList;
import java.util.UUID;

public class DBManager {
    public static final String TAG = "DBManager";
    private static DBManager mInstance = null;
    private Context mContext = null;
    private DBHandler mDBHandler;
    private UserModel mUser;
    private ArrayList<WalletModel> mWalletList;
    private WalletModel mWallet;
    private ArrayList<TransactionModel> mTransList;
    private TransactionModel mTrans;

    public UserModel getUser() {
        return mUser;
    }

//    public void setUser(UserModel mUser) {
//        this.mUser = mUser;
//    }

    public ArrayList<WalletModel> getWalletList() {
        return this.mWalletList;
    }
//    public void setWalletList(ArrayList<WalletModel> walletList) {
//        this.mWalletList = walletList;
//    }

    public WalletModel getWallet() {
        return mWallet;
    }

//    public void setWallet(WalletModel mWallet) {
//        this.mWallet = mWallet;
//    }

    public ArrayList<TransactionModel> getTransList() {
        return mTransList;
    }

//    public void setTransList(ArrayList<TransactionModel> mTransList) {
//        this.mTransList = mTransList;
//    }

    public TransactionModel getTrans() {
        return mTrans;
    }

//    public void setTrans(TransactionModel mTrans) {
//        this.mTrans = mTrans;
//    }


    public static DBManager getInstance() {
        if (null == mInstance) {
            synchronized (DBManager.class) {
                if (null == mInstance) {
                    mInstance = new DBManager();
                }
            }
        }
        return mInstance;
    }

    public void init(Context context){
        mContext = context;
        mDBHandler = new DBHandler(mContext);
        mUser = new UserModel();
        mWallet = new WalletModel();
        mTransList = new ArrayList<>();
        mTrans = new TransactionModel();
    }

//    USER API
    public boolean Login(String userName, String password) {
        return mDBHandler.CheckUser(userName, password);
    }
    public boolean Signup(String userName, String password) {
        return mDBHandler.AddUser(userName, password);
    }

    public UserModel GetUserInfo(String userName, String password) {
        DBUserModel dbUser = mDBHandler.GetUserByInfo(userName, password);
        ArrayList<DBWalletModel> dbWalletList = mDBHandler.GetAllWalletsByUserId(dbUser.getId());
        ArrayList<WalletModel> walletList = new ArrayList<>();
        for(DBWalletModel dbWallet : dbWalletList) {
            CommonValue.WalletType eType = dbWallet.getType();
            String description = dbWallet.getDescription();
            ArrayList<DBTransactionModel> dbTransList = mDBHandler.GetAllTransactionsByWalletId(dbWallet.getId());
            ArrayList<TransactionModel> transList = new ArrayList<>();
            for(DBTransactionModel dbTrans : dbTransList) {
                transList.add(dbTrans.ToTransactionModel());
            }
            walletList.add(new WalletModel(transList, eType, description));
        }
        mWalletList = walletList;
        UserModel retUser = new UserModel(walletList, userName);
        mUser = retUser;
        return retUser;
    }

//    WALLET API
    public ArrayList<WalletModel> GetAllWalletsOfUser(UserModel user) {
        mUser = user;
        mWalletList = user.getWalletList();
        return user.getWalletList();
    }

    public WalletModel GetWalletInfo(UserModel user, CommonValue.WalletType type) {
        mUser = user;
        WalletModel retWallet = new WalletModel();
        for(WalletModel wallet : mUser.getWalletList()) {
            if (wallet.getWalletType().getInt() == type.getInt()) {
                retWallet = wallet;
                mWallet = wallet;
                break;
            }
        }
        return  retWallet;
    }

    public boolean AddWallet(UserModel user, WalletModel wallet) {
        mUser = user;
        UUID userId =
    }

//    TRANSACTION API
    public ArrayList<TransactionModel> GetAllTransactionsOfWallet()

    public TransactionModel GetTransactionInfo() {
        mTrans = mDBHandler.GetTransactionById(1);
        return mTrans;
    }


}
