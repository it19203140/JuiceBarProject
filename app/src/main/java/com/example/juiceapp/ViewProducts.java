package com.example.juiceapp;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
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
                CharSequence[] items = {"Update", "Delete", "Peek"};

                final AlertDialog.Builder dialog = new AlertDialog.Builder(ViewProducts.this);

                dialog.setTitle("Choose an action");
                dialog.setItems(items, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (i == 0) {
                            //Update
                            Cursor cursor = db.getAllProductIDs();
                            ArrayList<String> prodID = new ArrayList<>();
                            while (cursor.moveToNext()) {
                                prodID.add(cursor.getString(0));
                            }
                            //show update dialog
                            int x = Integer.parseInt(prodID.get(position));
                            showDialogUpdate(ViewProducts.this,x);

                        }
                        if (i == 1){
                            //Delete
                            Cursor cursor = db.getAllProductIDs();
                            ArrayList<String> prodID = new ArrayList<>();
                            while (cursor.moveToNext()) {
                                prodID.add(cursor.getString(0));
                            }
                            int x = Integer.parseInt(prodID.get(position));
                            showDialogDelete(x);
                        }

                        if(i == 2){
                            //Peek
                            AlertDialog.Builder dialogPeek = new AlertDialog.Builder(ViewProducts.this);
                            dialog.setTitle("Peek");
                            dialog.setMessage("Do you wish to get more information on the product ?");
                            dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    Product product = new Product();
                                    product = adaper.getItem(position);
                                    Intent intent = new Intent(ViewProducts.this,PeekProductActivity.class);
                                    intent.putExtra("ProductID", product.getID());
                                    intent.putExtra("ProductName", product.getName());
                                    intent.putExtra("ProductIngredients", product.getIngredients());
                                    intent.putExtra("ProductType", product.getType());
                                    intent.putExtra("ProductRating", product.getRating());
                                    intent.putExtra("ProductPrice", product.getPrice());
                                    startActivity(intent);
                                }
                            });
                            dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                            dialog.show();
                        }
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    private void showDialogDelete(final int productID) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(ViewProducts.this);
        dialogDelete.setTitle("Warning !");
        dialogDelete.setMessage("Are you sure you want to delete ?");
        dialogDelete.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try {
                    db.deleteProduct(productID);
                    Toast.makeText(ViewProducts.this, "Deleted product", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e) {
                    Log.e("Error", e.getMessage());
                }
                updateProductList();
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

        //get clicked row data from sqlite
        Product product;
        product = db.getProductViaID(position);
        editName.setText(product.getName());
        editPrice.setText(product.getPrice());
        editRating.setText(product.getRating());
        editIngredients.setText(product.getIngredients());
        editType.setText(product.getType());

        //Setting width of dialog
        int width = (int) (activity.getResources().getDisplayMetrics().widthPixels*0.95);

        //Setting height of dialog
        int height = (int) (activity.getResources().getDisplayMetrics().heightPixels*0.8);

        dialog.getWindow().setLayout(width, height);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                String ingredients = editIngredients.getText().toString().trim();
                String price = editPrice.getText().toString().trim();
                String rating = editRating.getText().toString().trim();
                String type = editType.getText().toString().trim();
                
                boolean check = validationSuccessful(name, price, ingredients, rating);
                
                if(check) {
                    Product product = new Product(name, type, price, rating, ingredients);
                    try {
                        db.updateProduct(product, position);
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Updated details", Toast.LENGTH_SHORT).show();
                    } catch (Exception error) {
                        Log.e("Update Unsuccessfull", error.getMessage());
                    }
                    updateProductList();
                }
                else {
                    Toast.makeText(ViewProducts.this, "Details not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }



    private void updateProductList() {
        //get all data from sqlite again

        ArrayList <Product> updatedProducts;
        updatedProducts = db.getAllProducts();
        products.clear();

        for(Product P : updatedProducts) {
            products.add(P);
        }
        adaper.notifyDataSetChanged();
    }

    public boolean validationSuccessful (String name, String price, String ingredients, String rating ) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ViewProducts.this);

        if(TextUtils.isEmpty(name)) {
            alertDialog.setMessage("The name field is empty ! Please re-enter");
            alertDialog.setTitle("Invalid Product Name");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(true);
            alertDialog.show();
            return false;
        } else if (TextUtils.isEmpty(price)) {
            alertDialog.setMessage("The price field is empty ! Please re-enter");
            alertDialog.setTitle("Invalid Product Price");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(true);
            alertDialog.show();
            return false;
        } else if (Double.parseDouble(price) == 0 || Double.parseDouble(price) < 0 ) {
            alertDialog.setMessage("The price "+price+" is invalid ! Please re-enter");
            alertDialog.setTitle("Invalid Product Price");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(true);
            alertDialog.show();
            return false;
        } else if (TextUtils.isEmpty(ingredients)) {
            alertDialog.setMessage("The Ingredients field is empty ! Please re-enter");
            alertDialog.setTitle("Invalid Product Ingredients");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(true);
            alertDialog.show();
            return false;
        } else if (TextUtils.isEmpty(rating)) {
            alertDialog.setMessage("The rating field is empty ! Please re-enter");
            alertDialog.setTitle("Invalid Product Rating");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(true);
            alertDialog.show();
            return false;
        } else if ( Double.parseDouble(rating) < 0 ||  Double.parseDouble(rating) == 0) {
            alertDialog.setMessage("The rating "+rating+" is invalid ! Please re-enter");
            alertDialog.setTitle("Invalid Product Rating");
            alertDialog.setPositiveButton("OK", null);
            alertDialog.setCancelable(true);
            alertDialog.show();
            return false;
        }
        else
            return true;
    }
}