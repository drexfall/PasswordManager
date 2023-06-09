package com.example.passwordmanager;

import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;

import java.util.ArrayList;
import java.util.List;

public class PasswordDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "passwords.db";
    private static final int DATABASE_VERSION = 1;

    public PasswordDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE passwords ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT, "
                + "password TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS passwords");
        onCreate(db);
    }

    public void addPassword(String username, String password,String website,String type) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        values.put("website", website);
        values.put("type", type);
        db.insert("passwords", null, values);
        db.close();
    }

    public void updatePassword(int id, String username, String password) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password", password);
        db.update("passwords", values, "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deletePassword(int id) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("passwords", "id=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public ArrayList<Password> getPasswords() {
        ArrayList<Password> passwords = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("passwords", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(PasswordContract.PasswordEntry._ID)));
                String username = cursor.getString(cursor.getColumnIndexOrThrow(PasswordContract.PasswordEntry.COLUMN_NAME_USERNAME));
                String password = cursor.getString(cursor.getColumnIndexOrThrow(PasswordContract.PasswordEntry.COLUMN_NAME_PASSWORD));
                String website = cursor.getString(cursor.getColumnIndexOrThrow(PasswordContract.PasswordEntry.COLUMN_NAME_WEBSITE));
                String type = cursor.getString(cursor.getColumnIndexOrThrow(PasswordContract.PasswordEntry.COLUMN_NAME_TYPE));
                Password p = new Password(id, username, password, website, type);
                passwords.add(p);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return passwords;
    }
}

