package com.example.juiceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InsertProduct extends AppCompatActivity {

    DatabaseHelperClass myDb;
    EditText editName, editPrice, editIng, editRating, editType;
    Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_product);
        myDb = new DatabaseHelperClass(this);

        editName = (EditText) findViewById(R.id.productName);
        editPrice = (EditText) findViewById(R.id.productPrice);
        editIng = (EditText) findViewById(R.id.productIngredients);
        editRating = (EditText) findViewById(R.id.productRating);
        editType = (EditText) findViewById(R.id.productType);
        btnAddProduct = (Button) findViewById(R.id.addProduct);
        addProduct();
    }

    public void addProduct() {
        btnAddProduct.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertProduct(editName.getText().toString(), editPrice.getText().toString(),
                                editIng.getText().toString(), editRating.getText().toString(), editType.getText().toString());

                        if (isInserted == true)
                            Toast.makeText(InsertProduct.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(InsertProduct.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}