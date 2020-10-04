package com.example.juiceapp;


import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngrediantViewActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ingrediant);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        IngrediantsDatabaseHelperClass databaseHelperClass = new IngrediantsDatabaseHelperClass(this);
        List<IngrediantModelClass> employeeModelClasses = databaseHelperClass.getEmployeeList();

        if (employeeModelClasses.size() > 0){
            IngrediantAdapterClass employeadapterclass = new IngrediantAdapterClass(employeeModelClasses, IngrediantViewActivity.this);
            recyclerView.setAdapter(employeadapterclass);
        }else {
            Toast.makeText(this, "There is no ingredient in the database", Toast.LENGTH_SHORT).show();
        }




    }
}
