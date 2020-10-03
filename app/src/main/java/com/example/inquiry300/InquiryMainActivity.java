package com.example.inquiry300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InquiryMainActivity extends AppCompatActivity {

    EditText editText_inquiry,editText_email;
    Button button_add,button_view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inquiry_activity_main);

        editText_inquiry = findViewById(R.id.edittext_name);
        editText_email = findViewById(R.id.edittext_email);
        button_add = findViewById(R.id.button_add);
        button_view = findViewById(R.id.button_view);


        button_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringInquiry = editText_inquiry.getText().toString();
                String stringEmail = editText_email.getText().toString();

                if (stringInquiry.length() <=0 || stringEmail.length() <=0){
                    Toast.makeText(InquiryMainActivity.this, "Enter All Data", Toast.LENGTH_SHORT).show();
                }else {
                    InquiryDatabaseHelperClass databaseHelperClass = new InquiryDatabaseHelperClass(InquiryMainActivity.this);
                    InquiryModelClass inquiryModelClass = new InquiryModelClass(stringInquiry,stringEmail);
                    databaseHelperClass.addInquiry(inquiryModelClass);
                    Toast.makeText(InquiryMainActivity.this, "Inquiry Submitted", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(getIntent());
                }
            }
        });


        button_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InquiryMainActivity.this, ViewInquiryActivity.class);
                startActivity(intent);
            }
        });


    }
}
