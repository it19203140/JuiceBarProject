package com.example.juiceBarProject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewIngredient extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ingedient);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        DatabaseHelperClass databaseHelperClass = new DatabaseHelperClass(this);
        List<IngredientModelClass> ingredientModelClasses = databaseHelperClass.getEmployeeList();

        if (ingredientModelClasses.size() > 0){
            IngredientAdapterClass employeadapterclass = new IngredientAdapterClass(ingredientModelClasses, ViewIngredient.this);
            recyclerView.setAdapter(employeadapterclass);
        }else {
            Toast.makeText(this, "There is no ingredient in the database", Toast.LENGTH_SHORT).show();
        }




    }
}
