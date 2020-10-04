package com.example.juiceBarProject;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class IngredientAdapterClass extends RecyclerView.Adapter<IngredientAdapterClass.ViewHolder> {

    List<IngredientModelClass> employee;
    Context context;
    DatabaseHelperClass databaseHelperClass;

    public IngredientAdapterClass(List<IngredientModelClass> employee, Context context) {
        this.employee = employee;
        this.context = context;
        databaseHelperClass = new DatabaseHelperClass(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_ingredient_item_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final IngredientModelClass ingredientModelClass = employee.get(position);

        holder.textViewID.setText(Integer.toString(ingredientModelClass.getId()));
        holder.editText_Name.setText(ingredientModelClass.getName());
        holder.editText_Quantity.setText(ingredientModelClass.getQuantity());
        holder.editText_Supplier.setText(ingredientModelClass.getSupplier());
        holder.editText_Date.setText(ingredientModelClass.getDate());

        holder.button_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringName = holder.editText_Name.getText().toString();
                String stringQuantity = holder.editText_Quantity.getText().toString();
                String stringSupplier = holder.editText_Supplier    .getText().toString();
                String stringDate = holder.editText_Date.getText().toString();

                databaseHelperClass.updateEmployee(new IngredientModelClass(ingredientModelClass.getId(),stringName,stringQuantity,stringSupplier,stringDate));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        holder.button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelperClass.deleteEmployee(ingredientModelClass.getId());
                employee.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return employee.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText editText_Name;
        EditText editText_Quantity;
        EditText editText_Supplier;
        EditText editText_Date;
        Button button_Edit;
        Button button_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            editText_Name = itemView.findViewById(R.id.edittext_name);
            editText_Quantity = itemView.findViewById(R.id.edittext_quantity);
            editText_Supplier = itemView.findViewById(R.id.edittext_supplier);
            editText_Date = itemView.findViewById(R.id.edittext_date);
            button_delete = itemView.findViewById(R.id.button_delete);
            button_Edit = itemView.findViewById(R.id.button_edit);

        }
    }
}
