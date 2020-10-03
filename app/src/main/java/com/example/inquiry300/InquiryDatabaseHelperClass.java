package com.example.inquiry300;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class InquiryDatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "inquiry301";
    //Database Table name
    private static final String TABLE_NAME = "EMPLOYEE";
    //Table columns
    public static final String ID = "id";
    public static final String INQUIRY = "inquiry";
    public static final String EMAIL = "email";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + INQUIRY + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL);";
    //Constructor
    public InquiryDatabaseHelperClass(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //Add Employee Data
    public void addInquiry(InquiryModelClass inquiryModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(InquiryDatabaseHelperClass.INQUIRY, inquiryModelClass.getInquiry());
        contentValues.put(InquiryDatabaseHelperClass.EMAIL, inquiryModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(InquiryDatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<InquiryModelClass> getInquiryList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<InquiryModelClass> storeInquiry = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String inquiry = cursor.getString(1);
                String email = cursor.getString(2);
                storeInquiry.add(new InquiryModelClass(id,inquiry,email));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeInquiry;
    }

    public void updateInquiry(InquiryModelClass inquiryModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(InquiryDatabaseHelperClass.INQUIRY,inquiryModelClass.getInquiry());
        contentValues.put(InquiryDatabaseHelperClass.EMAIL,inquiryModelClass.getEmail());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(inquiryModelClass.getId())});
    }

    public void deleteInquiry(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}