package com.example.juiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;


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
        String createTable = "CREATE TABLE  " + TABLE_NAME + " (PRODUCT_ID INTEGER PRIMARY KEY AUTOINCREMENT, PRODUCT_NAME TEXT, PRICE TEXT, INGREDIENTS TEXT, RATING TEXT, TYPE TEXT) ";
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String dropTable = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(dropTable);
        onCreate(db);
    }

    public boolean insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, product.getName());
        contentValues.put(COL_2, product.getPrice());
        contentValues.put(COL_3, product.getIngredients());
        contentValues.put(COL_4, product.getRating());
        contentValues.put(COL_5, product.getType());

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else
            return true;
    }

    public Product getProductViaID (int id) {
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " +COL_0+ " = " + id;
        Cursor cursor = db.rawQuery(selectQuery, null);
        Product product = new Product();

        cursor.moveToFirst();
        product.setID(cursor.getString(0));
        product.setName(cursor.getString(1));
        product.setPrice(cursor.getString(2));
        product.setIngredients(cursor.getString(3));
        product.setRating(cursor.getString(4));
        product.setType(cursor.getString(5));

        cursor.close();
        return product;
    }

    public void updateProduct(Product product, int position) {

        SQLiteDatabase db = getWritableDatabase();

        //Query for updating data
        String updateQuery = "UPDATE product_table SET PRODUCT_NAME=?, PRICE=?, INGREDIENTS=?, RATING=?, TYPE=? WHERE PRODUCT_ID = ?";

        SQLiteStatement statement = db.compileStatement(updateQuery);
        statement.clearBindings();

        statement.bindString(1, product.getName());
        statement.bindString(2, product.getPrice());
        statement.bindString(3, product.getIngredients());
        statement.bindString(4, product.getRating());
        statement.bindString(5, product.getType());
        statement.bindDouble(6, position);

        statement.execute();
        db.close();
    }
    public void deleteProduct(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String deleteQuery = "DELETE FROM product_table WHERE PRODUCT_ID=?";

        SQLiteStatement statement = db.compileStatement(deleteQuery);
        statement.clearBindings();
        statement.bindDouble(1,id);

        statement.execute();
        db.close();
    }

    public Cursor getProduct(String SQL) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(SQL, null);
    }



    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> prodList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setID(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setPrice(cursor.getString(2));
                product.setIngredients(cursor.getString(3));
                product.setRating(cursor.getString(4));
                product.setType(cursor.getString(5));
                prodList.add(product);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return prodList;
    }
}
