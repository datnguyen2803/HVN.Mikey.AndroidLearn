package com.example.clonemoneylover.database;

import com.example.clonemoneylover.UserModel;

import java.util.UUID;

public class DBUserModel {
    private UUID mId;
    private String mEmail;
    private String mPassword;
    private String mName;


    public UUID getId() {
        return mId;
    }
    public void setId(UUID id) {
        this.mId = id;
    }
    public String getEmail() {
        return mEmail;
    }
    public void setEmail(String email) {
        this.mEmail = email;
    }
    public String getPassword() {
        return mPassword;
    }
    public void setPassword(String password) {
        this.mPassword = password;
    }
    public String getName() {
        return mName;
    }
    public void setName(String name) {
        this.mName = name;
    }


    public DBUserModel() {
        mId = new UUID(0L, 0L);
        this.mEmail = null;
        this.mPassword = null;
        this.mName = null;
    }

    public DBUserModel(String email, String password, String name) {
        mId = new UUID(0L, 0L);
        this.mEmail = email;
        this.mPassword = password;
        this.mName = name;
    }

    public DBUserModel(UUID id, String email, String password, String name) {
        mId = new UUID(0L, 0L);
        this.mId = id;
        this.mEmail = email;
        this.mPassword = password;
        this.mName = name;
    }
}
