package com.example.sa.JuiceBar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;



public class VegjuiceFragment extends Fragment {


    public VegjuiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.vegjuice_frag, container, false);

        ArrayList<VegjuiceClass> sea_food = new ArrayList<VegjuiceClass>();
        sea_food.add(new VegjuiceClass("Tomato juice", "300 Rs", R.drawable.tomatojuice,"0"));
        sea_food.add(new VegjuiceClass("Carat juice", "250 Rs", R.drawable.caratjuice,"0"));
        sea_food.add(new VegjuiceClass("Beetroot juice", "200 Rs", R.drawable.beetrootjuice,"0"));
        sea_food.add(new VegjuiceClass("Mix veg juice", "300 Rs", R.drawable.mixvegjuice,"0"));

        VegjuiceAdapter seaadapter = new VegjuiceAdapter(getActivity(),sea_food);

        ListView listView = view.findViewById(R.id.listview_sea);
        listView.setAdapter(seaadapter);





        return view;
    }

}
