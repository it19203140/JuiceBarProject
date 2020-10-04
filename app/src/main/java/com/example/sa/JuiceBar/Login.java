package com.example.sa.JuiceBar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;




public class Login extends AppCompatActivity {

    private EditText editTextUsername,editTextPassword;
    private Button buttonLogin;
    private TextView textViewsignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        editTextUsername=findViewById(R.id.edittext_username);
        editTextPassword=findViewById(R.id.edittext_password);
        buttonLogin=findViewById(R.id.button_login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ButtonLogin_onClick(view);
            }
        });


        textViewsignup=findViewById(R.id.textview_registration);
        textViewsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Signup_onClick(view);
            }
        });

    }

    public void ButtonLogin_onClick(View view){
        AccountDB accountDB =new AccountDB(getApplicationContext());
        String username=editTextUsername.getText().toString();
        String password=editTextPassword.getText().toString();
        Account account=accountDB.login(username,password);
        if(account == null){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(R.string.can_not_login);
            builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();

        }else{
            Intent intent=new Intent(Login.this,MainActivity.class);
            intent.putExtra("account",account);
            startActivity(intent);

        }


    }

    public void Signup_onClick(View view){
        Intent intent= new Intent(Login.this, Register.class);
        startActivity(intent);
    }

}