package com.example.juiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHelperClass extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "juiceapp.db";
    public static final String TABLE_NAME = "product_table";
    public static final String COL_0 = "PRODUCT_ID";
    public static final String COL_1 = "PRODUCT_NAME";
    public static final String COL_2 = "PRICE";
    public static final String COL_3 = "INGREDIENTS";
    public static final String COL_4 = "RATING";
    public static final String COL_5 = "TYPE";

    public DatabaseHelperClass(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCT_NAME VARCHAR(20), PRICE REAL, INGREDIENTS TEXT, RATING INTEGER, TYPE VARCHAR(20))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertProduct(String name, String price, String ing, String rating, String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, price);
        contentValues.put(COL_3, ing);
        contentValues.put(COL_4, rating);
        contentValues.put(COL_5, type);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Cursor getAllProducts() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }
}
