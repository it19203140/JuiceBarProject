package com.example.juiceshopproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.juiceshopproject.entities.Account;

public class AccountDB extends SQLiteOpenHelper {

    private static String dbName="accountDB";
    private static String tableName="account";
    private static String idColumn="id";
    private static String usernameColumn="username";
    private static String passwordColumn="password";
    private static String fullNameColumn="fullname";
    private static String emailColumn="email";



    public AccountDB(Context context){
            super(context,dbName,null,1);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("create table " + tableName + " ( " +
                idColumn + " integer primary key autoincrement, " +
                usernameColumn + " text, " +
                passwordColumn + " text, " +
                fullNameColumn + " text, " +
                emailColumn + " text " +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
               onCreate(sqLiteDatabase);
    }

    public boolean create (Account account){
        boolean result=true;
        try{
            SQLiteDatabase sqLiteDatabase=getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(usernameColumn,account.getUsername());
            contentValues.put(passwordColumn,account.getPassword());
            contentValues.put(fullNameColumn,account.getFullname());
            contentValues.put(emailColumn,account.getEmail());
            result=sqLiteDatabase.insert(tableName,null,contentValues) > 0;

        }catch (Exception e){
            result=false;
        }
        return result;
    }

    public boolean update (Account account){
        boolean result=true;
        try{
            SQLiteDatabase sqLiteDatabase=getWritableDatabase();
            ContentValues contentValues=new ContentValues();
            contentValues.put(usernameColumn,account.getUsername());
            contentValues.put(passwordColumn,account.getPassword());
            contentValues.put(fullNameColumn,account.getFullname());
            contentValues.put(emailColumn,account.getEmail());
            result=sqLiteDatabase.update(tableName,contentValues,idColumn+ " = ?",
                    new String[] {String.valueOf(account.getId())}) > 0;

        }catch (Exception e){
            result=false;
        }
        return result;
    }


    public Account login(String username,String password){
         Account account=null;
         try {
                SQLiteDatabase sqLiteDatabase=getReadableDatabase();
                Cursor cursor=sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ? and password = ?",
                        new String[]  {username,password});
                if(cursor.moveToFirst()){
                    account=new Account();
                    account.setId(cursor.getInt(0));
                    account.setUsername(cursor.getString(1));
                    account.setPassword(cursor.getString(2));
                    account.setFullname(cursor.getString(3));
                    account.setEmail(cursor.getString(4));

                }
         }catch (Exception e){
             account=null;
         }
        return account;
    }

    public Account find(int id){
        Account account=null;
        try {
            SQLiteDatabase sqLiteDatabase=getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from " + tableName + " where id = ?",
                    new String[]  { String.valueOf(id) });
            if(cursor.moveToFirst()){
                account=new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setFullname(cursor.getString(3));
                account.setEmail(cursor.getString(4));

            }
        }catch (Exception e){
            account=null;
        }
        return account;
    }


    public Account checkUsername(String username){
        Account account=null;
        try {
            SQLiteDatabase sqLiteDatabase=getReadableDatabase();
            Cursor cursor=sqLiteDatabase.rawQuery("select * from " + tableName + " where username = ?",
                    new String[]  {username});
            if(cursor.moveToFirst()){
                account=new Account();
                account.setId(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setFullname(cursor.getString(3));
                account.setEmail(cursor.getString(4));

            }
        }catch (Exception e){
            account=null;
        }
        return account;
    }

}
