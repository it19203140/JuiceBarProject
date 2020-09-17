package com.example.juiceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertProduct extends AppCompatActivity {

    DatabaseHelperClass myDb;
    EditText editName, editPrice, editIng, editRating;
    Button btnAddProduct;
    Spinner spinner_productType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        myDb = new DatabaseHelperClass(this);
        initializeObjects();

        addProduct();
    }

    public void initializeObjects() {
        editName = (EditText) findViewById(R.id.productName);
        editPrice = (EditText) findViewById(R.id.productPrice);
        editIng = (EditText) findViewById(R.id.productIngredients);
        editRating = (EditText) findViewById(R.id.productRating);
        btnAddProduct = (Button) findViewById(R.id.addProduct);
        spinner_productType = (Spinner) findViewById(R.id.productType);
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(InsertProduct.this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.types));

        //Changing layout.simple_list_item_1 to dropdown

        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_productType.setAdapter(myAdapter);
    }

    public void addProduct() {
        btnAddProduct.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        String productType = spinner_productType.getSelectedItem().toString();

                        boolean isInserted = myDb.insertProduct(editName.getText().toString(), editPrice.getText().toString(),
                                editIng.getText().toString(), editRating.getText().toString(), productType);

                        if (isInserted)
                            Toast.makeText(InsertProduct.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(InsertProduct.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}