package com.example.clonemoneylover;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Locale;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "CloneMoneyLover.db";
    private static final int DB_VERSION = 1;
    private static final String DB_USER_TABLE = "UserTable";
    private static final String DB_WALLET_TABLE = "WalletTable";
    private static final String DB_TRANSACTION_TABLE = "TransactionTable";

    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_NAME = "name";
    private static final String USER_COLUMN_PASSWORD = "password";

    private static final String WALLET_COLUMN_ID = "id";
    private static final String WALLET_COLUMN_USER_ID = "user_id";
    private static final String WALLET_COLUMN_BALANCE = "balance";
    private static final String WALLET_COLUMN_TYPE = "type";
    private static final String WALLET_COLUMN_DESCRIPTION = "description";

    private static final String TRANSACTION_COLUMN_ID = "id";
    private static final String TRANSACTION_COLUMN_WALLET_ID = "wallet_id";
    private static final String TRANSACTION_COLUMN_MONEY = "money";
    private static final String TRANSACTION_COLUMN_TYPE = "type";
    private static final String TRANSACTION_COLUMN_DATE = "date";
    private static final String TRANSACTION_COLUMN_TIME = "time";
    private static final String TRANSACTION_COLUMN_DESCRIPTION = "description";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        CreateTable(DB_USER_TABLE);
        CreateTable(DB_WALLET_TABLE);
        CreateTable(DB_TRANSACTION_TABLE);

//        for (int i = 1; i <= 1; i++)
//        {
//            CommonValue.TransactionMinorType eMinorType = CommonValue.TransactionMinorType.eTRANSFER_OUT_FOOD_AND_DRINK;
//            TransactionType transType = new TransactionType(eMinorType);
//            AddTransaction(new TransactionModel(i*1000, transType, "trans from" + i));
//        }

    }

    private final String TAG = "SQLite";


    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    private void CreateTable(String tableName) {
        String sqlProperties = "";
        switch (tableName) {
            case DB_USER_TABLE:
            {
                sqlProperties += String.format( "(" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s TEXT NOT NULL, " +
                                "%s TEXT NOT NULL" +
                                ")", USER_COLUMN_ID, USER_COLUMN_NAME, USER_COLUMN_PASSWORD);
            }
            break;

            case DB_WALLET_TABLE:
            {
                sqlProperties += String.format( "(" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s INTEGER NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s TEXT NOT NULL" +
                                ")", WALLET_COLUMN_ID, WALLET_COLUMN_USER_ID, WALLET_COLUMN_BALANCE, WALLET_COLUMN_TYPE, WALLET_COLUMN_DESCRIPTION);
            }
            break;

            case DB_TRANSACTION_TABLE:
            {
                sqlProperties += String.format("(" +
                                "%s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                "%s INTEGER NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s TEXT NOT NULL, " +
                                "%s TEXT NOT NULL, " +
                                "%s TEXT " +
                                ")", TRANSACTION_COLUMN_ID, TRANSACTION_COLUMN_WALLET_ID, TRANSACTION_COLUMN_MONEY, TRANSACTION_COLUMN_TYPE, TRANSACTION_COLUMN_DATE, TRANSACTION_COLUMN_TIME, TRANSACTION_COLUMN_DESCRIPTION);
            }
            break;

            default:
            {
                Log.d(TAG, "Error: Not handle creating wrong table" + tableName);
            }
            break;
        }
        String sqlQuery = String.format("CREATE TABLE IF NOT EXISTS %s %s", tableName, sqlProperties);
//        Log.d(TAG, "SQLite query = " + sqlQuery);
        this.getWritableDatabase().execSQL(sqlQuery);
    }

    public ArrayList<TransactionModel> GetAllTransactions() {
        ArrayList<TransactionModel> retList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format("SELECT * FROM %s", DB_TRANSACTION_TABLE);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        while(cursor.isAfterLast() == false){
            int money = cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_MONEY));
            TransactionType type = new TransactionType(CommonValue.TransactionMinorType.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_TYPE))));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_DESCRIPTION));

            TransactionModel currTrans = new TransactionModel (money, type, description);
            retList.add(currTrans);
            cursor.moveToNext();
        }
        cursor.close();

        return retList;
    }

    public TransactionModel GetTransactionById(int id) {
//        TransactionModel retTrans = new TransactionModel();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format("SELECT * FROM %s where %s=%d", DB_TRANSACTION_TABLE, TRANSACTION_COLUMN_ID, id);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        int a = cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_MONEY);
        int b = cursor.getInt(a);

        int money = cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_MONEY));
        TransactionType type = new TransactionType(CommonValue.TransactionMinorType.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_TYPE))));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_DESCRIPTION));
        TransactionModel retTrans = new TransactionModel (money, type, description);
        cursor.close();

        return retTrans;
    }

    public int AddTransaction(TransactionModel trans) {
        SQLiteDatabase db = this.getWritableDatabase();
        int walletId = 999;
        int money = trans.getMoney();
        int type = trans.getTransType().getMinorType().getInt();
        String date = trans.getDate().toString();
        String time = trans.getTime().toString();
        String description = trans.getDescription();

//        method 1: using insert()
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(TRANSACTION_COLUMN_WALLET_ID, walletId);
//        contentValues.put(TRANSACTION_COLUMN_MONEY, money);
//        contentValues.put(TRANSACTION_COLUMN_TYPE, type);
//        contentValues.put(TRANSACTION_COLUMN_DATE, date);
//        contentValues.put(TRANSACTION_COLUMN_TIME, time);
//        contentValues.put(TRANSACTION_COLUMN_DESCRIPTION, description);
//        db.insert(DB_TRANSACTION_TABLE, null, contentValues);

//        method 2: using execSQL()
        String sqlQuery = String.format(Locale.US,
                "INSERT INTO %s (%s, %s, %s, %s, %s, %s) " +
                "VALUES (%d, %d, %d, \"%s\", \"%s\", \"%s\")",
                DB_TRANSACTION_TABLE,
                TRANSACTION_COLUMN_WALLET_ID, TRANSACTION_COLUMN_MONEY, TRANSACTION_COLUMN_TYPE, TRANSACTION_COLUMN_DATE, TRANSACTION_COLUMN_TIME, TRANSACTION_COLUMN_DESCRIPTION,
                walletId, money, type, date, time, description
        );
        db.execSQL(sqlQuery);

        return 1;
    }

    public void UpdateTransaction(int id, TransactionModel trans) {
        SQLiteDatabase db = this.getWritableDatabase();
        int money = trans.getMoney();
        int type = trans.getTransType().getMinorType().getInt();
        String date = trans.getDate().toString();
        String time = trans.getTime().toString();
        String description = trans.getDescription();

//        method 1: using update()
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(TRANSACTION_COLUMN_MONEY, money);
//        contentValues.put(TRANSACTION_COLUMN_TYPE, type);
//        contentValues.put(TRANSACTION_COLUMN_DATE, date);
//        contentValues.put(TRANSACTION_COLUMN_TIME, time);
//        contentValues.put(TRANSACTION_COLUMN_DESCRIPTION, description);
//        String sqlCondition = String.format("id=%d", id);
//        db.update(DB_TRANSACTION_TABLE, contentValues, sqlCondition, null);

//        method 2: using execSQL()
        String sqlQuery = String.format(
                "UPDATE %d " +
                "SET %s = %d, %s = %d, %s = \"%s\", %s = \"%s\", %s = \"%s\"",
                DB_TRANSACTION_TABLE,
                TRANSACTION_COLUMN_MONEY, money, TRANSACTION_COLUMN_TYPE, type, TRANSACTION_COLUMN_DATE, date, TRANSACTION_COLUMN_TIME, time, TRANSACTION_COLUMN_DESCRIPTION, description
        );
        db.execSQL(sqlQuery);

    }

    public void DeleteTransaction(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

//        method 1: using delete()
//        String sqlCondition = String.format("id=%d", id);
//        db.delete(DB_TRANSACTION_TABLE, sqlCondition, null);

//        method 2: using execSQL()
        String sqlQuery = String.format("DELETE FROM %s WHERE %s = %d", DB_TRANSACTION_TABLE, TRANSACTION_COLUMN_ID, id);
        db.execSQL(sqlQuery);
    }
}
