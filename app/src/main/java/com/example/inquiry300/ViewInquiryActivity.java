package com.example.inquiry300;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class ViewInquiryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_inquiry);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        InquiryDatabaseHelperClass databaseHelperClass = new InquiryDatabaseHelperClass(this);
        List<InquiryModelClass> inquiryModelClasses = databaseHelperClass.getInquiryList();

        if (inquiryModelClasses.size() > 0){
            InquiryAdapterClass iadapterclass = new InquiryAdapterClass(inquiryModelClasses, ViewInquiryActivity.this);
            recyclerView.setAdapter(iadapterclass);
        }else {
            Toast.makeText(this, "There are no Inquiries Submitted", Toast.LENGTH_SHORT).show();
        }




    }
}
