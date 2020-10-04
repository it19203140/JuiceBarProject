package com.example.juiceapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class IngrediantsDatabaseHelperClass extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "ingrediant_database";
    //Database Table name
    private static final String TABLE_NAME = "Ingr";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String QUANTITY = "quantity";
    public static final String SUPPLIER = "supplier";
    public static final String DATE = "date";
    private SQLiteDatabase sqLiteDatabase;


    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL," + QUANTITY + " TEXT NOT NULL," + SUPPLIER + " TEXT NOT NULL," + DATE + " TEXT NOT NULL);";
    //Constructor
    public IngrediantsDatabaseHelperClass(Context context){
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
    public void addEmployee(IngrediantModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IngrediantsDatabaseHelperClass.NAME, employeeModelClass.getName());
        contentValues.put(IngrediantsDatabaseHelperClass.QUANTITY, employeeModelClass.getQuantity());
        contentValues.put(IngrediantsDatabaseHelperClass.SUPPLIER, employeeModelClass.getSupplier());
        contentValues.put(IngrediantsDatabaseHelperClass.DATE, employeeModelClass.getDate());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert(IngrediantsDatabaseHelperClass.TABLE_NAME, null,contentValues);
    }

    public List<IngrediantModelClass> getEmployeeList(){
        String sql = "select * from " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();
        List<IngrediantModelClass> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);
                String quantity = cursor.getString(2);
                String supplier = cursor.getString(3);
                String date = cursor.getString(4);
                storeEmployee.add(new IngrediantModelClass(id,name,quantity,supplier,date));
            }while (cursor.moveToNext());
        }
        cursor.close();
        return storeEmployee;
    }

    public void updateEmployee(IngrediantModelClass employeeModelClass){
        ContentValues contentValues = new ContentValues();
        contentValues.put(IngrediantsDatabaseHelperClass.NAME,employeeModelClass.getName());
        contentValues.put(IngrediantsDatabaseHelperClass.QUANTITY,employeeModelClass.getQuantity());
        contentValues.put(IngrediantsDatabaseHelperClass.SUPPLIER,employeeModelClass.getSupplier());
        contentValues.put(IngrediantsDatabaseHelperClass.DATE,employeeModelClass.getDate());
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID + " = ?" , new String[]
                {String.valueOf(employeeModelClass.getId())});
    }

    public void deleteEmployee(int id){
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(TABLE_NAME, ID + " = ? ", new String[]
                {String.valueOf(id)});
    }

}