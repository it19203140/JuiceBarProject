package com.example.juiceapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PeekProductActivity extends AppCompatActivity {

    TextView textViewProdID, textViewProdName, textViewProdIng, textViewProdType, textViewProdRat, textViewProdPrice;

    TextView textViewEnteredPrice, textViewMargin, textViewServiceTax, textViewVAT, textViewSellingPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peek_product);

        String productID = getIntent().getStringExtra("ProductID");
        String productName = getIntent().getStringExtra("ProductName");
        String productIngredients = getIntent().getStringExtra("ProductIngredients");
        String productType = getIntent().getStringExtra("ProductType");
        String productRating = getIntent().getStringExtra("ProductRating");
        String productPrice = getIntent().getStringExtra("ProductPrice");

        initializeObjects();

        textViewProdID.setText(productID);
        textViewProdName.setText(productName);
        textViewProdRat.setText(productRating);
        textViewProdType.setText(productType);
        textViewProdPrice.setText(productPrice);
        textViewProdIng.setText(productIngredients);

        textViewEnteredPrice.setText("Rs. "+productPrice);

        //getting calculation values through the static function
        double vat = 2;
        double margin = 5;
        double serviceTax = 5;

        double [] taxValues = new double[4];
        taxValues = Product.calculateProductPrice(Double.parseDouble(productPrice),vat,margin,serviceTax);
        int sellingPrice = Product.sellingPrice(taxValues);

        textViewMargin.setText("Rs. "+ Math.round(taxValues[1]));
        textViewServiceTax.setText("Rs. "+ Math.round(taxValues[2]));
        textViewVAT.setText("Rs. "+ Math.round(taxValues[3]));
        textViewSellingPrice.setText("Rs. "+ sellingPrice);
    }

    private void initializeObjects() {
        textViewProdID = findViewById(R.id.TextViewPeekProdIDValue);
        textViewProdName = findViewById(R.id.TextViewPeekProdNameValue);
        textViewProdPrice = findViewById(R.id.TextViewPeekProdPriceValue);
        textViewProdIng = findViewById(R.id.TextViewPeekProdIngredientsValue);
        textViewProdType = findViewById(R.id.TextViewPeekProdTyoeValue);
        textViewProdRat = findViewById(R.id.TextViewPeekProdRatingValue);

        textViewEnteredPrice = findViewById(R.id.prodPriceValue);
        textViewMargin = findViewById(R.id.marginValue);
        textViewServiceTax = findViewById(R.id.serviceTaxValue);
        textViewVAT = findViewById(R.id.VATValue);
        textViewSellingPrice = findViewById(R.id.finalPriceValue);
    }


}