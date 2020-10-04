package com.example.juiceapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;



public class AdminMainActivity2 extends AppCompatActivity {

    private EditText editTextUsername,editTextEmail;
    private EditText editTextFullName,editTextPassword;
    private Button buttonRegister;
    private TextView textViewlogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_activity_main2);


        editTextUsername=findViewById(R.id.edittext_username);
        editTextEmail=findViewById(R.id.edittext_Email);
        editTextFullName=findViewById(R.id.edittext_FullName);
        editTextPassword=findViewById(R.id.edittext_password);
        buttonRegister=findViewById(R.id.button_Register);
        textViewlogin=findViewById(R.id.textview_Login);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonRegister_onClick(view);
            }
        });

        textViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(AdminMainActivity2.this, AdminMainActivity.class);
                startActivity(intent2);
            }
        });

        textViewlogin=findViewById(R.id.textview_Login);


    }


    public void buttonRegister_onClick(View view) {
        try{
            AdminAccountDB accountDB=new AdminAccountDB(getApplicationContext());
            AdminAccount account=new AdminAccount();
            account.setUsername(editTextUsername.getText().toString());
            account.setEmail(editTextEmail.getText().toString());
            account.setFullname(editTextFullName.getText().toString());
            account.setPassword(editTextPassword.getText().toString());
            AdminAccount temp=accountDB.checkUsername(editTextUsername.getText().toString());

            if(temp == null) {
                if (accountDB.create(account)) {
                    Intent intent = new Intent(AdminMainActivity2.this, AdminMainActivity.class);
                    startActivity(intent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                    builder.setTitle(R.string.error);
                    builder.setMessage(R.string.can_not);
                    builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    builder.show();
                }
            }else{
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle(R.string.error);
                builder.setMessage(R.string.can_not_use_username);
                builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
                builder.show();

            }
        }catch (Exception e){
            AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
            builder.setTitle(R.string.error);
            builder.setMessage(e.getMessage());
            builder.setPositiveButton(R.string.Ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
            builder.show();

        }


    }

}
