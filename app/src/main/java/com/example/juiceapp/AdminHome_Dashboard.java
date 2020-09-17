package com.example.juiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminHome_Dashboard extends AppCompatActivity {

    CardView inquiriesCard;
    CardView ordersCard;
    CardView productsCard;
    CardView ingredientsCard;
    CardView editProfileCard;
    CardView aboutUsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home__dashboard);
        productsCard = findViewById(R.id.cardViewProductsID);
        redirectToProducts();

    }

    public void redirectToProducts() {
        productsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(AdminHome_Dashboard.this, ViewProducts.class));
            }
        });
    }
}