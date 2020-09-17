package com.example.juiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class ViewProducts extends AppCompatActivity {

    ImageView addProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_products);
        addProduct = findViewById(R.id.imageViewAddProductID);
        redirectToInsertProductActivity();
    }

    public void redirectToInsertProductActivity() {
        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ViewProducts.this, InsertProduct.class));
            }
        });
    }
}