package com.example.juiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class AdminHome_Dashboard extends AppCompatActivity {

    CardView inquiriesCard;
    CardView ordersCard;
    CardView productsCard;
    CardView ingredientsCard;
    CardView editProfileCard;
    CardView aboutUsCard;
    private AdminAccount account;
    private TextView textViewWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home__dashboard);
        productsCard = findViewById(R.id.cardViewProductsID);
        editProfileCard=findViewById(R.id.cardViewEditProfileID);
        inquiriesCard=findViewById(R.id.cardViewInquiryID);
        ingredientsCard=findViewById(R.id.cardViewIngredientsID);
        textViewWelcome=findViewById(R.id.textview_Welcome);
        redirectToProducts();


        Intent intent=getIntent();
        account=(AdminAccount) intent.getSerializableExtra("account");
        textViewWelcome.setText(getString(R.string.Welcome) + " " + account.getUsername());


        editProfileCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(AdminHome_Dashboard.this, AdminEditProfileActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        ingredientsCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11=new Intent(AdminHome_Dashboard.this, IngrediantMainActivity.class);
                startActivity(intent11);
            }
        });
        inquiriesCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent11=new Intent(AdminHome_Dashboard.this, InquiryMainActivity.class);
                startActivity(intent11);
            }
        });



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