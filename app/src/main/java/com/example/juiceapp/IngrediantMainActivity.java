package com.example.juiceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IngrediantMainActivity extends AppCompatActivity {

    EditText editText_name,editText_quantity,editText_supplier,editText_date;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ingrediant_activity_main);

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
                    Toast.makeText(IngrediantMainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    IngrediantsDatabaseHelperClass databaseHelperClass = new IngrediantsDatabaseHelperClass(IngrediantMainActivity.this);
                    IngrediantModelClass employeeModelClass = new IngrediantModelClass(stringName,stringQuantity,stringSupplier,stringDate);
                    databaseHelperClass.addEmployee(employeeModelClass);
                    Toast.makeText(IngrediantMainActivity.this, "Ingredient Added Successfully", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IngrediantMainActivity.this, IngrediantViewActivity.class);
                startActivity(intent);
            }
        });


    }
}
