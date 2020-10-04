package com.example.juiceBarProject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class IngredientMainActivity extends AppCompatActivity {

    EditText editText_name,editText_quantity,editText_supplier,editText_date;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_name = findViewById(R.id.edittext_name);
        editText_quantity = findViewById(R.id.edittext_quantity);
        editText_supplier = findViewById(R.id.edittext_supplier);
        editText_date = findViewById(R.id.edittext_date);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = editText_name.getText().toString();
                String stringQuantity = editText_quantity.getText().toString();
                String stringSupplier = editText_supplier.getText().toString();
                String stringDate = editText_date.getText().toString();

                if (stringName.length() <=0 || stringQuantity.length() <=0 || stringSupplier.length() <=0 || stringDate.length() <=0){
                    Toast.makeText(IngredientMainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(IngredientMainActivity.this);
                    IngredientModelClass ingredientModelClass = new IngredientModelClass(stringName,stringQuantity,stringSupplier,stringDate);
                    databaseHelperClass.addEmployee(ingredientModelClass);
                    Toast.makeText(IngredientMainActivity.this, "Ingredient Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


       button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngredientMainActivity.this, ViewIngredient.class);
                startActivity(intent);
            }
        });


    }
}
