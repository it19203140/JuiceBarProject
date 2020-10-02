package com.example.juiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class InsertProduct extends AppCompatActivity {

    public static DatabaseHelperClass myDb;
    private EditText editName, editPrice, editIng, editRating;
    private Button btnAddProduct;
    private Spinner spinner_productType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);

        //initialized database
        myDb = new DatabaseHelperClass(this);
        initializeObjects();
        addProduct();
    }

    public void initializeObjects() {
        editName = findViewById(R.id.productName);
        editPrice = findViewById(R.id.productPrice);
        editIng = findViewById(R.id.productIngredients);
        editRating = findViewById(R.id.productRating);
        btnAddProduct = findViewById(R.id.addProduct);
        spinner_productType = findViewById(R.id.productType);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(InsertProduct.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.types));

        //Changing layout.simple_list_item_1 to dropdown

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_productType.setAdapter(myAdapter);
    }

    public boolean validationSuccessful (String name, String price, String ingredients, String rating ) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(InsertProduct.this);

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

    public void addProduct() {
        btnAddProduct.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String type = spinner_productType.getSelectedItem().toString();
                        String name = editName.getText().toString();
                        String price = editPrice.getText().toString();
                        String ingredients = editIng.getText().toString();
                        String rating = editRating.getText().toString();

                        boolean check;

                        check = validationSuccessful(name,price,ingredients, rating);

                        if(check) {
                            Product product = new Product(name, type, price, rating, ingredients);
                            boolean isInserted = myDb.insertProduct(product);

                            if (isInserted) {
                                Toast.makeText(InsertProduct.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(InsertProduct.this, ViewProducts.class);
                                startActivity(intent);
                            }
                            else
                                Toast.makeText(InsertProduct.this, "Data Not Inserted", Toast.LENGTH_LONG).show();

                        }
                    }
                }
        );
    }
}