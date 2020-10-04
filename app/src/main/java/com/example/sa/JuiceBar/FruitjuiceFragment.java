package com.example.sa.JuiceBar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;



public class FruitjuiceFragment extends Fragment {
    DatabaseHelper mydb;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fruitjuice_frag, container, false);
        this.mydb = new DatabaseHelper(getContext());
        ArrayList<FruitjuiceClass> c_food = new ArrayList<FruitjuiceClass>();
        c_food.add(new FruitjuiceClass("Pineapple juice", "150 Rs", R.drawable.pineapplejuice,"0"));
        c_food.add(new FruitjuiceClass("Orange juice", "150 Rs", R.drawable.orangejuice,"0"));
        c_food.add(new FruitjuiceClass("Watermelon juice", "250 Rs", R.drawable.watermelonjuice,"0"));
        c_food.add(new FruitjuiceClass("Grapes juice", "200 Rs", R.drawable.grapesjuice,"0"));


        FruitjuiceAdapter cadapter = new FruitjuiceAdapter(getActivity(),c_food);


        ListView listView = view.findViewById(R.id.listview_juices);
        listView.setAdapter(cadapter);



        return view;
    }





}
