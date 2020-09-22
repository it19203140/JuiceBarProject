package com.example.juiceapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ViewProducts extends AppCompatActivity {

    private static final String TAG = "ViewProducts";
    ArrayList <Product> products = new ArrayList<>();
    DatabaseHelperClass db;
    ImageView redirectToAddProductActivity;
    ProductListAdaper adaper = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        Log.d(TAG, "conCreate: Started.");
        initializeObjects();
        retrieveProductListFromDB();
        redirectToInsertProductActivity();
    }

    public void redirectToInsertProductActivity() {
        redirectToAddProductActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProducts.this, InsertProduct.class));
            }
        });
    }

    public void initializeObjects() {
        redirectToAddProductActivity = findViewById(R.id.imageViewAddProductID);
        listView = findViewById(R.id.products_list_view_id);
    }

    public void retrieveProductListFromDB () {
        db = new DatabaseHelperClass(this);

        products = db.getAllProducts();

        //Custom ProductListAdapter
        adaper = new ProductListAdaper(this, R.layout.adapter_view_layout, products);
        listView.setAdapter(adaper);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                //Alert dialog to display options of update and delete
                CharSequence[] items = {"Update", "Delete"};

                AlertDialog.Builder dialog = new AlertDialog.Builder(ViewProducts.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            //Update
                            Cursor cursor = db.getProduct("SELECT PRODUCT_ID FROM product_table");
                            ArrayList<Integer> prodID = new ArrayList<Integer>();
                            while (cursor.moveToNext()) {
                                int val = Integer.parseInt(cursor.getString(0));
                                prodID.add(val);
                            }
                            //show update dialog
                            showDialogUpdate(ViewProducts.this,prodID.get(position));

                        }
                        if (i == 1){
                            //Delete
                            Cursor cursor = db.getProduct("SELECT PRODUCT_ID FROM product_table");
                            ArrayList<Integer> prodID = new ArrayList<Integer>();
                            while (cursor.moveToNext()) {
                                int val = Integer.parseInt(cursor.getString(0));
                                prodID.add(val);
                            }
                            showDialogDelete(prodID.get(position));
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void showDialogDelete(int id) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ViewProducts.this);
        dialogDelete.setTitle("Warning !");
        dialogDelete.setMessage("Are you sure you want to delete ?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    db.deleteProduct(i);
                    Toast.makeText(ViewProducts.this, "Deleted product", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
            }
        });
        dialogDelete.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        dialogDelete.show();
    }

    private void showDialogUpdate(Activity activity, final int position) {
        final Dialog dialog = new Dialog(activity);
        dialog.setContentView(R.layout.activity_update_product_dialog);
        dialog.setTitle("Update");

        final EditText editName = dialog.findViewById(R.id.updateProductName);
        final EditText editIngredients = dialog.findViewById(R.id.updateProductIngredients);
        final EditText editPrice = dialog.findViewById(R.id.updateProductPrice);
        final EditText editRating = dialog.findViewById(R.id.updateProductRating);
        final EditText editType = dialog.findViewById(R.id.updateProductType);
        Button updateBtn = dialog.findViewById(R.id.updateProductBtn);

        //Setting width of dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels*0.95);

        //Setting height of dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels*0.8);
        dialog.getWindow().setLayout(width, height);
        dialog.show();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String name = editName.getText().toString().trim();
                    String ingredients = editIngredients.getText().toString().trim();
                    String price = editPrice.getText().toString().trim();
                    String rating = editRating.getText().toString().trim();
                    String type = editType.getText().toString().trim();

                    Product product = new Product(name, type, price, rating, ingredients);
                    db.updateProduct(product);
                    dialog.dismiss();
                    Toast.makeText(ViewProducts.this, "Updated details", Toast.LENGTH_SHORT).show();
                }
                catch (Exception error) {
                    Log.e("Update Unsuccessfull", error.getMessage());
                }
                updateProductList();
            }
        });

    }

    private void updateProductList() {
        //get all data from sqlite again

        Cursor cursor = db.getProduct("SELECT * FROM product_table");
        products.clear();

        if (cursor.moveToFirst()) {
            do {
                Product product = new Product();
                product.setID(cursor.getString(0));
                product.setName(cursor.getString(1));
                product.setPrice(cursor.getString(2));
                product.setIngredients(cursor.getString(3));
                product.setRating(cursor.getString(4));
                product.setType(cursor.getString(5));
                products.add(product);
            } while (cursor.moveToNext());
        }

        adaper.notifyDataSetChanged();

    }
}