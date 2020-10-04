package com.example.sa.JuiceBar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class OrderPage extends AppCompatActivity {

    final ArrayList<OrderClass> list = new ArrayList<OrderClass>();
    DatabaseHelper mydb;
    OrderAdapter adapter = null;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_page);


        ListView listView = findViewById(R.id.order_page);
        mydb = new DatabaseHelper(this);


         //use to store data from cursor
        final Cursor data = mydb.Get_OrderDetails();  //contain all data

        if (data.getCount() == 0) {


        } else {
            while (data.moveToNext()) {

                list.add(new OrderClass(data.getString(0), data.getString(1), data.getString(2), data.getString(3)));

            }
        }

        adapter = new OrderAdapter(getApplicationContext(), list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                CharSequence[] items = {"Delete", "Update"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(OrderPage.this);
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        if (i == 0){
                            Cursor c =mydb.getDataa("SELECT OrderNo FROM OrderDetails");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()){
                                arrID.add(c.getInt(0));
                            }
                            showDialogDelete(arrID.get(position));
                        }

                        if(i == 1) {
                            Cursor c = mydb.getDataa("SELECT OrderNo FROM OrderDetails");
                            ArrayList<Integer> arrID = new ArrayList<>();
                            while (c.moveToNext()) {
                                arrID.add(c.getInt(0));
                            }

                            showEditDialog(OrderPage.this,arrID.get(position));
                        }
                    }
                });
                dialog.show();
                return false;
            }
        });

    }

    private void showEditDialog(Activity activity,final int Orderno) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.update_dialog);
        dialog.setTitle("Update Qty");

        final EditText editQty = dialog.findViewById(R.id.editTextQty);
        final TextView tvOrderNo = dialog.findViewById(R.id.dialogOrderID);
        final TextView tvItemName = dialog.findViewById(R.id.dialogItemName);
        final   TextView tvItemPrice = dialog.findViewById(R.id.dialogItemPrice);
        Button updateBtn = dialog.findViewById(R.id.updateBtn);

        //get clicked row from database
        OrderClass order;
        order = mydb.getproductViaID(Orderno);

        tvOrderNo.setText(order.getItemId());
        tvItemName.setText(order.getItemName());
        tvItemPrice.setText(order.getItemPrice());
        editQty.setText(order.getItemquantity());


        int width = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.95);
        int height = (int)(activity.getResources().getDisplayMetrics().widthPixels*0.8);

        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String orderNo =tvOrderNo.getText().toString().trim();
                String itemQty = editQty.getText().toString().trim();

                //OrderClass order = new OrderClass(orderNo, itemName, itemPrice, itemQty);

                mydb.updateProduct(orderNo, itemQty);
                dialog.dismiss();
                Toast.makeText(OrderPage.this, "Updated QTY", Toast.LENGTH_SHORT).show();

                updateArray();
            }
        });
    }

    private void updateArray() {
        list.clear();

        Cursor data = mydb.Get_OrderDetails();

        if (data.getCount() == 0) {

        } else {
            while (data.moveToNext()) {
                list.add(new OrderClass(data.getString(0), data.getString(1), data.getString(2), data.getString(3)));
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void showDialogDelete(final int OrderNo) {
        final AlertDialog.Builder dialogDelete = new AlertDialog.Builder(OrderPage.this);
        dialogDelete.setTitle("Warning");
        dialogDelete.setMessage("Are u want delete this item? ");
        dialogDelete.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    mydb.deleteData(OrderNo);
                    Toast.makeText(OrderPage.this,"Deleted Successfully ",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Log.e("error",e.getMessage());
                }
                updateArray();
            }
        });
        dialogDelete.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

}






























 /*   public void Order_data(){
        View order;
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res =   mydb.Get_OrderDetails();
                if(res.getCount() == 0) {
                    showmessage("Error", "nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){

                    buffer.append("Id : " + res.getString(0) + "\n");
                    buffer.append("Item Name : " + res.getString(1) + "\n");
                    buffer.append("Quantity : " + res.getString(2)+ "\n");
                    buffer.append("Price : " + res.getString(3)+ "\n");

                }
                showmessage("Data",buffer.toString());


            }
        });
    }
    public void DeleteAll() {
        delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            mydb.delete_all();
        }
    });


    }


        public void showmessage(String title,String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

*/
