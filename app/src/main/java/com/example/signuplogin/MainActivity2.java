package com.example.signuplogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity2 extends AppCompatActivity {

    EditText mTextUsername;
    EditText mTextContact;
    EditText mTextEmail;
    EditText mTextPassword;
    EditText mTextConfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db=new Database(this);
        mTextUsername = (EditText) findViewById(R.id.edittext_username);
        mTextContact=(EditText) findViewById(R.id.edittext_contact);
        mTextEmail=(EditText) findViewById(R.id.edittext_Email);
        mTextPassword = (EditText) findViewById(R.id.edittext_password);
        mTextConfPassword=(EditText) findViewById(R.id.edittext_conf_password);
        mButtonRegister=(Button) findViewById(R.id.button_Register);
        mTextViewLogin=(TextView) findViewById(R.id.textview_Login);

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cont=mTextContact.getText().toString().trim();
                String eml=mTextEmail.getText().toString().trim();
                String cnf_pwd =  mTextConfPassword.getText().toString().trim();

                if(pwd.equals(cnf_pwd)){
                    long val = db.addUser(user,pwd,cont,eml);
                    if(val > 0){
                        Toast.makeText(MainActivity2.this,"You have registered", Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(MainActivity2.this,MainActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(MainActivity2.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(MainActivity2.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}