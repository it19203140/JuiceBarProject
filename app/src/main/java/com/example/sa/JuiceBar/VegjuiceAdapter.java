package com.example.sa.JuiceBar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class VegjuiceAdapter extends ArrayAdapter<VegjuiceClass> {
    int quantity = 0; int i = 0;
    int value = 0; String hold = "";
    int pos = 0, counter = 1;
    String [] order_details = new String[1000];
    DatabaseHelper mydb;
    String Number,Name,Quantity,Price = "";


    public VegjuiceAdapter(Activity context, ArrayList<VegjuiceClass> sea_food) {
        super(context, 0, sea_food);
        this.mydb = new DatabaseHelper(context.getApplicationContext());

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.menu_design, parent, false);
        }


        VegjuiceClass currentVegjuice = getItem(position);

        TextView nameTextView = listItemView.findViewById(R.id.item_name);
        nameTextView.setText(currentVegjuice.getItemName());

        ImageView imageView = listItemView.findViewById(R.id.item_image);
        imageView.setImageResource(currentVegjuice.getImageResourceId());

        TextView priceTextView = listItemView.findViewById(R.id.item_price);
        priceTextView.setText("Price " + currentVegjuice.getItemPrice());


        Button plus = listItemView.findViewById(R.id.plus_btn);
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity = quantity + 1;

            }
        });

        Button minus = listItemView.findViewById(R.id.minus_btn);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (quantity > 0)
                {
                    quantity = quantity - 1;
                }
                else {
                    quantity = quantity;
                }

            }
        });

        hold = currentVegjuice.getItemquantity();
        value = Integer.parseInt(hold);
        quantity = value + quantity;

        TextView quantityTextView = listItemView.findViewById(R.id.quantityTextView);
        quantityTextView.setText(String.valueOf(quantity));

        Button cart_btn = listItemView.findViewById(R.id.cart_btn);
        cart_btn.setTag(position);

        cart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cart_btn.setEnabled(false);
                pos = (Integer)view.getTag();
                if(quantity != 0) { //if quan < 0 or equals to 0
                    if (pos == 0) {
                        int price = 300;
                        int total = Calculation.calculateFinal(price, quantity);
                        boolean isinserted = mydb.Add_to_Cart("Tomato juice",String.valueOf(quantity),String.valueOf(total));
                        if (isinserted)
                        {

                            order_details[i] = "Id " + counter + " Tomato juice Price Rs " + 300 * quantity + " ";
                            counter++;  //var use for no of items order
                            i++;  //var uses to store data in array */
                            quantity = 0;  //holds the value for each item quantity*s/
                            Toast.makeText(getContext(), "Order Added Successfully !", Toast.LENGTH_SHORT).show();
                        }
                        else
                            Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();




                    }
                    if (pos == 1) {
                        int price = 250;
                        int total = Calculation.calculateFinal(price, quantity);
                        boolean isinserted =  mydb.Add_to_Cart("Carat juice",String.valueOf(quantity),String.valueOf(total));
                        if (isinserted)
                        {

                            order_details[i] = "Id " + counter + " Carat juice Price Rs " + 250 * quantity + " ";
                            counter++;
                            i++;
                            quantity = 0;
                            Toast.makeText(getContext(), "Order Added Successfully !", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();




                    }
                    if (pos == 2) {
                        int price = 200;
                        int total = Calculation.calculateFinal(price, quantity);
                        boolean isinserted = mydb.Add_to_Cart("Beetroot juice",String.valueOf(quantity),String.valueOf(total));
                        if (isinserted)
                        {

                            order_details[i] = "Id : " + counter + " Beetroot juice Price Rs " + 200 * quantity + " ";
                            counter++;
                            i++;
                            quantity = 0;
                            Toast.makeText(getContext(), "Order Added Successfully !", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();





                    }
                    if (pos == 3) {
                        int price = 300;
                        int total = Calculation.calculateFinal(price, quantity);
                        boolean isinserted = mydb.Add_to_Cart("Mix veg juice",String.valueOf(quantity),String.valueOf(total));
                        if (isinserted)
                        {

                            order_details[i] = "Id " + counter + " Mix veg juice Price Rs " + 300 * quantity + " ";
                            counter++;
                            i++;
                            quantity = 0;
                            Toast.makeText(getContext(), "Order Added Successfully !", Toast.LENGTH_SHORT).show();

                        }
                        else
                            Toast.makeText(getContext(), "Please, Try again", Toast.LENGTH_SHORT).show();





                    }


                } else {  Toast.makeText(getContext(), "Quantity value can't be zero or lesser!!!", Toast.LENGTH_SHORT).show();

                    int j = 0;
                    Toast.makeText(getContext(), "" + order_details[j] + "\n" + order_details[j+1] + "\n"+ order_details[j+2] + "\n" + order_details[j+3], Toast.LENGTH_LONG).show();

                }




            }
        });

        return listItemView;

    }
}