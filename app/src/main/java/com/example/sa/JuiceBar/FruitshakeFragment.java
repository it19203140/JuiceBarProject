package com.example.sa.JuiceBar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;



public class FruitshakeFragment extends Fragment {


   /* public FruitshakeFragment() {
        // Required empty public constructor
    }*/


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fruitshake_frag, container, false);

        ArrayList<FruitshakeClass> fastfood = new ArrayList<FruitshakeClass>();
        fastfood.add(new FruitshakeClass("Chocolate shake", "250 Rs", R.drawable.chocolateshake,"0"));
        fastfood.add(new FruitshakeClass("Kitkat shake", "220 Rs", R.drawable.kitkatshake,"0"));
        fastfood.add(new FruitshakeClass("Vanila shake", "350 Rs", R.drawable.vanilashake,"0"));
        fastfood.add(new FruitshakeClass("Oreo shake", "320 Rs", R.drawable.oreoshake,"0"));
        fastfood.add(new FruitshakeClass("Banana shake", "200 Rs", R.drawable.bananashake,"0"));
        fastfood.add(new FruitshakeClass("Greenapple shake", "250 Rs", R.drawable.greenappleshake,"0"));
        fastfood.add(new FruitshakeClass("Strawberry shake", "170 Rs", R.drawable.strawberryshake,"0"));
        fastfood.add(new FruitshakeClass("Papaya shake", "250 Rs", R.drawable.papayashake,"0"));
        fastfood.add(new FruitshakeClass("Mango shake", "200 Rs", R.drawable.mangoshake,"0"));

        FruitshakeAdapter fastfoodadapter = new FruitshakeAdapter(getActivity(),fastfood);

        ListView listView = view.findViewById(R.id.listview_fastfood);
        listView.setAdapter(fastfoodadapter);
        return view;

    }

}
