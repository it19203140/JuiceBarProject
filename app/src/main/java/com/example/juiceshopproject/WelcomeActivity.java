package com.example.juiceshopproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.juiceshopproject.entities.Account;

public class WelcomeActivity extends AppCompatActivity {

    private TextView textViewWelcome;
    private Button buttonEditProfile;
    private Button buttonLogout;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        buttonLogout=findViewById(R.id.LogOut);
        textViewWelcome=findViewById(R.id.textview_Welcome);
        Intent intent=getIntent();
        account=(Account) intent.getSerializableExtra("account");
        textViewWelcome.setText(getString(R.string.Welcome) + " " + account.getUsername());


        buttonEditProfile=findViewById(R.id.EditProfile);
        buttonEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent1=new Intent(WelcomeActivity.this,EditProfileActivity.class);
                    intent1.putExtra("account",account);
                    startActivity(intent1);
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(WelcomeActivity.this,MainActivity.class);
                startActivity(intent2);
            }
        });

    }
}