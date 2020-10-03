package com.example.juiceshopproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.juiceshopproject.database.AccountDB;
import com.example.juiceshopproject.entities.Account;

public class EditProfileActivity extends AppCompatActivity {

    private EditText editTextUsername,editTextEmail;
    private EditText editTextFullName,editTextPassword;
    private Button buttonSave;
    private Button buttonCancel;
    private Account account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);



        editTextUsername=findViewById(R.id.edittext_username);
        editTextEmail=findViewById(R.id.edittext_Email);
        editTextFullName=findViewById(R.id.edittext_FullName);
        editTextPassword=findViewById(R.id.edittext_password);
        loadData();

        buttonSave=findViewById(R.id.SaveProfileDetails);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonSave_onClick(view);
            }
        });


        buttonCancel=findViewById(R.id.GoBack);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(EditProfileActivity.this,WelcomeActivity.class);
                intent.putExtra("account",account);
                startActivity(intent);
            }
        });
    }

    public void buttonSave_onClick(View view) {
               try{
                   AccountDB accountDB=new AccountDB(getApplicationContext());
                   Account currentAccount=accountDB.find(account.getId());
                   String newUsername=editTextUsername.getText().toString();
                   Account temp=accountDB.checkUsername(newUsername);
                    if(!newUsername.equalsIgnoreCase(currentAccount.getUsername()) && temp != null){

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
                        return;
                    }
                       currentAccount.setUsername(editTextUsername.getText().toString());
                       currentAccount.setEmail(editTextUsername.getText().toString());
                       currentAccount.setFullname(editTextFullName.getText().toString());
                       String password=editTextPassword.getText().toString();
                       if(!password.isEmpty()){
                           currentAccount.setPassword(editTextPassword.getText().toString());
                       }
                       if(accountDB.update(currentAccount)){
                           Intent intent=new Intent(EditProfileActivity.this,WelcomeActivity.class);
                           intent.putExtra("account",currentAccount);
                           startActivity(intent);
                       }else{
                           AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                           builder.setTitle(R.string.error);
                           builder.setMessage(R.string.edit_profile1);
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

    private void loadData(){
        Intent intent=getIntent();
        account=(Account) intent.getSerializableExtra("account");
        editTextEmail.setText(account.getEmail());
        editTextFullName.setText(account.getFullname());
        editTextUsername.setText(account.getUsername());

    }

}