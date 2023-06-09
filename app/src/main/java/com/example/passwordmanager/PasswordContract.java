package com.example.passwordmanager;

import android.provider.BaseColumns;

public final class PasswordContract {
    private PasswordContract() {}

    public static class PasswordEntry implements BaseColumns {
        public static final String TABLE_NAME = "passwords";
        public static final String COLUMN_NAME_USERNAME = "username";
        public static final String COLUMN_NAME_PASSWORD = "password";
        public static final String COLUMN_NAME_WEBSITE = "website";
        public static final String COLUMN_NAME_TYPE = "type";


        public static final String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY," +
                        COLUMN_NAME_USERNAME + " TEXT," +
                        COLUMN_NAME_PASSWORD + " TEXT," +
                        COLUMN_NAME_WEBSITE + " TEXT," +
                        COLUMN_NAME_TYPE + " TEXT)";

        public static final String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}