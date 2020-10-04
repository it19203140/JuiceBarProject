package com.example.sa.JuiceBar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "Juicebar";

    public  static final String TABLE_NAME = "OrderDetails";
    public  static final String KEY_1 = "OrderNo";
    public  static final String KEY_2 = "ItemName";
    public  static final String KEY_3 = "ItemQuantity";
    public  static final String KEY_4 = "ItemPrice";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //db.execSQL("CREATE TABLE " + TABLE_NAME +" (id integer primary key autoincrement,name text,password text) "   );
        db.execSQL("CREATE TABLE " + TABLE_NAME +" (orderno integer primary key autoincrement,itemname text,itemquantity text,itemprice text) "   );


    }
    public OrderClass getproductViaID(int OrderNo){
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " +KEY_1+ " = " + OrderNo;

        // SQLiteStatement statement = db.compileStatement(selectQuery);
        //statement.bindString(1, String.valueOf(OrderNo));

        Cursor cursor = db.rawQuery(selectQuery,null);
        OrderClass order = new OrderClass();

        cursor.moveToFirst();
        order.setItemid(cursor.getString(0));
        order.setItemname(cursor.getString(1));
        order.setItemquantity(cursor.getString(2));
        order.setItemprice(cursor.getString(3));
        //order.String iId = cursor.getString(0);
        //String iName = cursor.getString(1);
        //String iQuantity = cursor.getString(2);
        //String iPrice = cursor.getString(3);

        cursor.close();
        return order;

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }




    public boolean Add_to_Cart(String Name,String Quantity,String Price){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(KEY_2,Name);
        contentValues1.put(KEY_3,Quantity);
        contentValues1.put(KEY_4,Price);
        double check = db.insert(TABLE_NAME,null,contentValues1);
        return check != -1;



    }
    public Cursor Get_OrderDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);
        return data;
    }

    public void deleteData(int OrderNo){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "DELETE FROM OrderDetails WHERE OrderNo=?";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();
        statement.bindDouble(1, OrderNo);

        statement.execute();
        database.close();
    }

    public Cursor getDataa(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql,null);
    }
    public void updateProduct(String id, String qty) {
        SQLiteDatabase database = getWritableDatabase();
        String updateQuery = "UPDATE OrderDetails SET ItemQuantity = ? WHERE OrderNo = ?";

        SQLiteStatement statement = database.compileStatement(updateQuery);

        statement.clearBindings();

        statement.bindString(1, qty);
        statement.bindString(2, id);

        statement.execute();
        database.close();
    }


}
