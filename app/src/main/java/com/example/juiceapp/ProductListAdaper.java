package com.example.juiceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProductListAdaper extends BaseAdapter {

    private static final String TAG = "ProductListAdaper";
    private Context mcontext;
    int resource;
    private ArrayList<Product> products;

    public ProductListAdaper(Context mcontext, int resource, ArrayList<Product> products) {
        this.mcontext = mcontext;
        this.resource = resource;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder{

        TextView tvID;
        TextView tvName;
        TextView tvType;
        TextView tvPrice;
        TextView tvRating;
        TextView tvIngredients;

    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null) {
            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(resource, null);
            holder.tvID = row.findViewById(R.id.AdaperViewProductID);
            holder.tvName = row.findViewById(R.id.AdaperViewProductName);
            holder.tvType = row.findViewById(R.id.AdaperViewProductType);
            holder.tvPrice = row.findViewById(R.id.AdaperViewProductPrice);
            row.setTag(holder);
        }
        else  {
            holder = (ViewHolder)row.getTag();
        }
        Product product = products.get(i);
        holder.tvID.setText("ID :" + product.getID());
        holder.tvName.setText(product.getName());
        holder.tvPrice.setText("Rs. "+ product.getPrice());
        holder.tvType.setText(product.getType());

        return  row;
    }
}
