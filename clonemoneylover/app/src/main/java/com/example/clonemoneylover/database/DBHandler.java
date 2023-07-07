package com.example.clonemoneylover.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.clonemoneylover.CommonValue;
import com.example.clonemoneylover.TransactionModel;
import com.example.clonemoneylover.TransactionType;
import com.example.clonemoneylover.UserModel;
import com.example.clonemoneylover.WalletModel;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.UUID;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "CloneMoneyLover.db";
    private static final int DB_VERSION = 1;
    private static final String DB_USER_TABLE = "UserTable";
    private static final String DB_WALLET_TABLE = "WalletTable";
    private static final String DB_TRANSACTION_TABLE = "TransactionTable";

    private static final String USER_COLUMN_ID = "id";
    private static final String USER_COLUMN_EMAIL = "email";
    private static final String USER_COLUMN_PASSWORD = "password";
    private static final String USER_COLUMN_NAME = "name";


    private static final String WALLET_COLUMN_ID = "id";
    private static final String WALLET_COLUMN_USER_ID = "user_id";
    private static final String WALLET_COLUMN_BALANCE = "balance";
    private static final String WALLET_COLUMN_TYPE = "type";
    private static final String WALLET_COLUMN_DESCRIPTION = "description";

    private static final String TRANSACTION_COLUMN_ID = "id";
    private static final String TRANSACTION_COLUMN_UI_ID = "ui_id";
    private static final String TRANSACTION_COLUMN_WALLET_ID = "wallet_id";
    private static final String TRANSACTION_COLUMN_MONEY = "money";
    private static final String TRANSACTION_COLUMN_TYPE = "type";
    private static final String TRANSACTION_COLUMN_DATE = "date";
    private static final String TRANSACTION_COLUMN_TIME = "time";
    private static final String TRANSACTION_COLUMN_DESCRIPTION = "description";

    public static final UUID UUID_EMPTY = new UUID(0L, 0L);
    public static final int EC_NOT_FOUND = -1;



    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

//        Clear data
        DropTable(DB_USER_TABLE);
        DropTable(DB_WALLET_TABLE);
        DropTable(DB_TRANSACTION_TABLE);

//        Create the tables
        CreateTable(DB_USER_TABLE);
        CreateTable(DB_WALLET_TABLE);
        CreateTable(DB_TRANSACTION_TABLE);

        InitSampleData();

    }

    private final String TAG = "SQLite";

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "DBHandler created" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DB_NAME);
        onCreate(db);
    }

    private void DropTable(String tableName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        db.close();
    }

    private void InitSampleData() {
//        DB_USER_TABLE

//        DB_WALLET_TABLE

//        DB_TRANSACTION_TABLE
//        for (int i = 1; i <= 1; i++)
//        {
//            CommonValue.TransactionMinorType eMinorType = CommonValue.TransactionMinorType.eTRANSFER_OUT_FOOD_AND_DRINK;
//            TransactionType transType = new TransactionType(eMinorType);
//            AddTransaction(new TransactionModel(i*1000, transType, "trans from" + i));
//        }
    }

    private void CreateTable(String tableName) {
        String sqlProperties = "";
        switch (tableName) {
            case DB_USER_TABLE:
            {
                sqlProperties += String.format( "(" +
                                "%s TEXT PRIMARY KEY, " +
                                "%s TEXT NOT NULL" +
                                "%s TEXT NOT NULL, " +
                                "%s TEXT NOT NULL" +
                                ")", USER_COLUMN_ID, USER_COLUMN_EMAIL, USER_COLUMN_PASSWORD, USER_COLUMN_NAME);
            }
            break;

            case DB_WALLET_TABLE:
            {
                sqlProperties += String.format( "(" +
                                "%s TEXT PRIMARY KEY, " +
                                "%s TEXT NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s TEXT NOT NULL" +
                                ")", WALLET_COLUMN_ID, WALLET_COLUMN_USER_ID, WALLET_COLUMN_BALANCE, WALLET_COLUMN_TYPE, WALLET_COLUMN_DESCRIPTION);
            }
            break;

            case DB_TRANSACTION_TABLE:
            {
                sqlProperties += String.format("(" +
                                "%s TEXT PRIMARY KEY, " +
                                "%s TEXT NOT NULL UNIQUE" +
                                "%s TEXT NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s INTEGER NOT NULL, " +
                                "%s TEXT NOT NULL, " +
                                "%s TEXT NOT NULL, " +
                                "%s TEXT " +
                                ")", TRANSACTION_COLUMN_ID, TRANSACTION_COLUMN_UI_ID, TRANSACTION_COLUMN_WALLET_ID, TRANSACTION_COLUMN_MONEY, TRANSACTION_COLUMN_TYPE, TRANSACTION_COLUMN_DATE, TRANSACTION_COLUMN_TIME, TRANSACTION_COLUMN_DESCRIPTION);
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

    private String Encode2SHA256(String input) {
        String shaResult = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            shaResult = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            // Handle the exception
        }
        return shaResult;
    }

//    USER TABLE API
    private UUID AchieveUserId(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=\"%s\"", DB_USER_TABLE, USER_COLUMN_EMAIL, email);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor == null) {
            return UUID_EMPTY;
        }

        cursor.moveToFirst();
        UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_ID)));
        cursor.close();

        return id;
    }
    private DBUserModel GetUser(UUID id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format(
                "SELECT * FROM %s WHERE %s=\"%s\"",
                DB_USER_TABLE, USER_COLUMN_ID, id);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        String email = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_EMAIL));
        String encodedPassword = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_PASSWORD));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_NAME));
        DBUserModel retDBUser = new DBUserModel(id, email, encodedPassword, name);
        cursor.close();

        return retDBUser;
    }
    public ArrayList<DBUserModel> GetAllUser() {
        ArrayList<DBUserModel> retList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format("SELECT * FROM %s", DB_USER_TABLE);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        while(!cursor.isAfterLast()){
            UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_ID)));
            String email = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_EMAIL));
            String encodedPassword = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_PASSWORD));
            String name = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_NAME));

            retList.add(new DBUserModel(id, email, encodedPassword, name));
            cursor.moveToNext();
        }
        cursor.close();

        return retList;
    }
    public DBUserModel GetUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String encodedPassword = Encode2SHA256(password);
        String sqlQuery = String.format(
                "SELECT * FROM %s " +
                "WHERE %s=\"%s\" AND %s=\"%s\"",
                DB_USER_TABLE,
                USER_COLUMN_EMAIL, email, USER_COLUMN_PASSWORD, encodedPassword);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor == null) {
            return null;
        }
        cursor.moveToFirst();
        UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_ID)));
        String name = cursor.getString(cursor.getColumnIndexOrThrow(USER_COLUMN_NAME));
        DBUserModel retDBUser = new DBUserModel(id, email, encodedPassword, name);
        cursor.close();

        return retDBUser;
    }
    public boolean CheckUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String encodedPassword = Encode2SHA256(password);
        String sqlQuery = String.format(
                "SELECT * FROM %s " +
                        "WHERE %s=\"%s\" AND %s=\"%s\"",
                DB_USER_TABLE,
                USER_COLUMN_EMAIL, email, USER_COLUMN_PASSWORD, encodedPassword);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor == null) {
            return false;
        } else {
            cursor.close();
            return true;
        }
    }
    public boolean AddUser(String email, String password, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = UUID.randomUUID();
        String encodedPassword = Encode2SHA256(password);

//        method 2: using execSQL()
        String sqlQuery = String.format(Locale.US,
                "INSERT INTO %s (%s, %s, %s, %s) " +
                "VALUES (\"%s\", \"%s\", \"%s\", \"%s\")",
                DB_USER_TABLE,
                USER_COLUMN_ID, USER_COLUMN_EMAIL, USER_COLUMN_PASSWORD, USER_COLUMN_NAME,
                id.toString(), email, encodedPassword, name
        );
        db.execSQL(sqlQuery);
        return true;
    }
    public boolean UpdateUser(DBUserModel updatedUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = updatedUser.getId();
        String email = updatedUser.getEmail();
        String password = updatedUser.getPassword();
        String name = updatedUser.getName();

//        method 2: using execSQL()
        String sqlQuery = String.format(Locale.US,
                "UPDATE %s " +
                "SET %s = \"%s\", %s = \"%s\", %s = \"%s\"" +
                "WHERE %s = \"%s\"",
                DB_USER_TABLE,
                USER_COLUMN_EMAIL, email, USER_COLUMN_PASSWORD, password, USER_COLUMN_NAME, name,
                USER_COLUMN_ID, id.toString()
        );
        db.execSQL(sqlQuery);
        return true;
    }
    public boolean RemoveUser(DBUserModel deletedUser) {
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = deletedUser.getId();

//        method 2: using execSQL()
        String sqlQuery = String.format(Locale.US,
                "DELETE FROM %s WHERE %s = \"%s\"", DB_USER_TABLE, USER_COLUMN_ID, id.toString());
        db.execSQL(sqlQuery);
        return true;
    }



//    WALLET TABLE API
    private UUID AchieveWalletId(String email, int type) {
        SQLiteDatabase db = this.getReadableDatabase();
        UUID userId = AchieveUserId(email);
        String sqlQuery = String.format(Locale.US,
                "SELECT * FROM %s " +
                        "WHERE %s=\"%s\" AND %s=\"%s\"",
                DB_WALLET_TABLE,
                WALLET_COLUMN_USER_ID, userId.toString(), WALLET_COLUMN_TYPE, type);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor == null) {
            return UUID_EMPTY;
        }

        cursor.moveToFirst();
        UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_ID)));
        cursor.close();

        return id;
    }
    private DBWalletModel GetWallet(UUID id) {
        String strId = id.toString();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=\"%s\"", DB_WALLET_TABLE, WALLET_COLUMN_ID, strId);
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        if(cursor == null) {
            return null;
        }

        UUID userId = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(WALLET_COLUMN_USER_ID)));
        int balance = cursor.getInt(cursor.getColumnIndexOrThrow(WALLET_COLUMN_BALANCE));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(WALLET_COLUMN_DESCRIPTION));
        CommonValue.WalletType type = CommonValue.WalletType.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(WALLET_COLUMN_TYPE)));

        cursor.close();

        return new DBWalletModel(id, userId, balance, type, description);
    }
    private ArrayList<DBWalletModel> GetAllWalletsOfUser(UUID userId) {
        ArrayList<DBWalletModel> retList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=\"%s\"", DB_WALLET_TABLE, WALLET_COLUMN_USER_ID, userId.toString());
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();

        if(cursor == null) {
            return new ArrayList<>();
        }

        while(!cursor.isAfterLast()){
            UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(WALLET_COLUMN_ID)));
            int balance = cursor.getInt(cursor.getColumnIndexOrThrow(WALLET_COLUMN_BALANCE));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(WALLET_COLUMN_DESCRIPTION));
            CommonValue.WalletType type = CommonValue.WalletType.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(WALLET_COLUMN_TYPE)));

            retList.add(new DBWalletModel(id, userId, balance, type, description));
            cursor.moveToNext();
        }
        cursor.close();

        return retList;
    }

    public ArrayList<DBWalletModel> GetAllWalletsOfUser(String email) {
        UUID userId = AchieveUserId(email);
        if(userId.equals(UUID_EMPTY)) {
            return new ArrayList<>();
        }
        return GetAllWalletsOfUser(userId);
    }
    public DBWalletModel GetWallet(String email, int type) {
        UUID walletId = AchieveWalletId(email, type);
        if(walletId.equals(UUID_EMPTY)) {
            return null;
        }
        return GetWallet(walletId);
    }

    public void AddWallet(UUID userId, int balance, CommonValue.WalletType walletType, String description) {
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = UUID.randomUUID();
        int type = walletType.getInt();

//        method 2: using execSQL()
        String sqlQuery = String.format(Locale.US,
                "INSERT INTO %s (%s, %s, %s, %s, %s) " +
                        "VALUES (\"%s\", \"%s\", %d, %d, \"%s\")",
                DB_WALLET_TABLE,
                WALLET_COLUMN_ID, WALLET_COLUMN_USER_ID, WALLET_COLUMN_BALANCE, WALLET_COLUMN_TYPE, WALLET_COLUMN_DESCRIPTION,
                id.toString(), userId.toString(), balance, type, description
        );
        db.execSQL(sqlQuery);
    }
    public void AddWallet(DBWalletModel dbWallet) {
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = UUID.randomUUID();
        UUID userId = dbWallet.getUserId();
        int type = dbWallet.getType().getInt();
        int balance = dbWallet.getBalance();
        String description = dbWallet.getDescription();

//        method 2: using execSQL()
        String sqlQuery = String.format(Locale.US,
                "INSERT INTO %s (%s, %s, %s, %s, %s) " +
                        "VALUES (\"%s\", \"%s\", %d, %d, \"%s\")",
                DB_WALLET_TABLE,
                WALLET_COLUMN_ID, WALLET_COLUMN_USER_ID, WALLET_COLUMN_BALANCE, WALLET_COLUMN_TYPE, WALLET_COLUMN_DESCRIPTION,
                id.toString(), userId.toString(), balance, type, description
        );
        db.execSQL(sqlQuery);
    }

//    TRANSACTION TABLE API
    private UUID AchieveTransactionId(UUID uiId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format(Locale.US,
                "SELECT * FROM %s " +
                        "WHERE %s=\"%s\"",
                        DB_TRANSACTION_TABLE,
                        TRANSACTION_COLUMN_UI_ID, uiId.toString());
        Cursor cursor = db.rawQuery(sqlQuery, null);
        if(cursor == null) {
            return UUID_EMPTY;
        }

        cursor.moveToFirst();
        UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_ID)));
        cursor.close();

        return id;
    }
    private DBTransactionModel GetTransactionById(UUID id) {
//        TransactionModel retTrans = new TransactionModel();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format(Locale.US,
                "SELECT * FROM %s WHERE %s=\"%s\"", DB_TRANSACTION_TABLE, TRANSACTION_COLUMN_ID, id.toString());
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        if(cursor == null) {
            return null;
        }
        UUID uiId = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_UI_ID)));
        UUID walletId = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_WALLET_ID)));
        int money = cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_MONEY));
        CommonValue.TransactionMinorType type = CommonValue.TransactionMinorType.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_TYPE)));
        LocalDate date = LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_DATE)));
        LocalTime time = LocalTime.parse(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_TIME)));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_DESCRIPTION));

        cursor.close();

        return new DBTransactionModel(id, uiId, walletId, money, type, date, time, description);
    }
    public ArrayList<DBTransactionModel> GetAllTransactions(UUID walletId) {
        ArrayList<DBTransactionModel> retList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String sqlQuery = String.format("SELECT * FROM %s WHERE %s=\"%s\"", DB_TRANSACTION_TABLE, TRANSACTION_COLUMN_WALLET_ID, walletId.toString());
        Cursor cursor = db.rawQuery(sqlQuery, null);
        cursor.moveToFirst();
        if(cursor == null) {
            return new ArrayList<>();
        }

        while(!cursor.isAfterLast()){
            UUID id = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_ID)));
            UUID uiId = UUID.fromString(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_UI_ID)));
            int money = cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_MONEY));
            CommonValue.TransactionMinorType type = CommonValue.TransactionMinorType.fromInt(cursor.getInt(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_TYPE)));
            LocalDate date = LocalDate.parse(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_DATE)));
            LocalTime time = LocalTime.parse(cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_TIME)));
            String description = cursor.getString(cursor.getColumnIndexOrThrow(TRANSACTION_COLUMN_DESCRIPTION));

            DBTransactionModel currTrans = new DBTransactionModel (id, uiId, walletId, money, type, date, time, description);
            retList.add(currTrans);
            cursor.moveToNext();
        }
        cursor.close();

        return retList;
    }
    public ArrayList<DBTransactionModel> GetAllTransactions(UUID userId, int type) {
        UUID walletId = AchieveWalletId(userId, type);
        if(walletId.equals(UUID_EMPTY)) {
            return new ArrayList<>();
        }
        return GetAllTransactions(walletId);
    }

    public boolean AddTransaction(DBTransactionModel newTrans) {
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = UUID.randomUUID();
        UUID walletId = newTrans.getWalletId();
        int money = newTrans.getMoney();
        int type = newTrans.getType().getInt();
        String date = newTrans.getDate().toString();
        String time = newTrans.getTime().toString();
        String description = newTrans.getDescription();

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
                "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) " +
                "VALUES (\"%s\", \"%s\", %d, %d, \"%s\", \"%s\", \"%s\")",
                DB_TRANSACTION_TABLE,
                TRANSACTION_COLUMN_ID, TRANSACTION_COLUMN_WALLET_ID, TRANSACTION_COLUMN_MONEY, TRANSACTION_COLUMN_TYPE, TRANSACTION_COLUMN_DATE, TRANSACTION_COLUMN_TIME, TRANSACTION_COLUMN_DESCRIPTION,
                id, walletId, money, type, date, time, description
        );
        db.execSQL(sqlQuery);

        return true;
    }
    public void UpdateTransaction(DBTransactionModel newTrans) {
        SQLiteDatabase db = this.getWritableDatabase();
        UUID id = newTrans.getId();
        UUID walletId = newTrans.getWalletId();
        int money = newTrans.getMoney();
        int type = newTrans.getType().getInt();
        String date = newTrans.getDate().toString();
        String time = newTrans.getTime().toString();
        String description = newTrans.getDescription();

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
        String sqlQuery = String.format(Locale.US,
                "UPDATE %s " +
                "SET %s = \"%s\", %s = %d, %s = %d, %s = \"%s\", %s = \"%s\", %s = \"%s\"" +
                "WHERE %s = \"%s\"",
                DB_TRANSACTION_TABLE,
                TRANSACTION_COLUMN_WALLET_ID, walletId.toString(), TRANSACTION_COLUMN_MONEY, money, TRANSACTION_COLUMN_TYPE, type, TRANSACTION_COLUMN_DATE, date, TRANSACTION_COLUMN_TIME, time, TRANSACTION_COLUMN_DESCRIPTION, description,
                TRANSACTION_COLUMN_ID, id.toString()
        );
        db.execSQL(sqlQuery);

    }
    public void DeleteTransaction(UUID id) {
        SQLiteDatabase db = this.getWritableDatabase();

//        method 1: using delete()
//        String sqlCondition = String.format("id=%d", id);
//        db.delete(DB_TRANSACTION_TABLE, sqlCondition, null);

//        method 2: using execSQL()
        String sqlQuery = String.format(Locale.US,
                "DELETE FROM %s WHERE %s = \"%s\"", DB_TRANSACTION_TABLE, TRANSACTION_COLUMN_ID, id.toString());
        db.execSQL(sqlQuery);
    }
}
