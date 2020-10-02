package com.example.juiceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class ProductListAdaper extends ArrayAdapter<Product> {

    private static final String TAG = "ProductListAdaper";
    private Context mcontext;
    int resource;

    /**
     *
     * @param context
     * @param resource
     * @param objects
     */
    public ProductListAdaper(Context context, int resource, ArrayList<Product> objects) {
        super(context, resource, objects);
        this.mcontext = context;
        this.resource = resource;
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        String ID = getItem(position).getID();
        String name = getItem(position).getName();
        String type = getItem(position).getType();
        String price = getItem(position).getPrice();
        String rating = getItem(position).getRating();
        String ingredients = getItem(position).getIngredients();

        Product product = new Product(name,type,price,rating,ingredients);
        product.setID(ID);
        LayoutInflater inflater = LayoutInflater.from(mcontext);
        convertView = inflater.inflate(resource,parent, false);

        TextView tvID = convertView.findViewById(R.id.AdaperViewProductID);
        TextView tvName = convertView.findViewById(R.id.AdaperViewProductName);
        TextView tvPrice = convertView.findViewById(R.id.AdaperViewProductPrice);
        TextView tvType = convertView.findViewById(R.id.AdaperViewProductType);

        double [] arr = new double[4];
        arr = Product.calculateProductPrice(Double.parseDouble(price), 5,15,8);

        int priceCount = Product.sellingPrice(arr);

        tvName.setText("" + name);
        tvPrice.setText("Rs. "+ priceCount);
        tvID.setText("ID : " + ID);
        tvType.setText(type);

        return convertView;
    }
}
