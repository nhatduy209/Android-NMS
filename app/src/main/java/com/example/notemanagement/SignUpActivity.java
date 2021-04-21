package com.example.notemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.notemanagement.DB.Account;
import com.example.notemanagement.DB.AccountLayer;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.Models.AccountModel;

import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        //handle event sign in
        signIn();
        signUp();
    }

    //add event click for button "Sign In"
    public void signIn(){
        Button signIn = (Button)findViewById(R.id.btn_sign_in_back);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //set intent to start SignInActivity
                Intent intent = new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
    }
    //handle event sign up
    public void signUp(){
        Button signUp = (Button)findViewById(R.id.btn_sign_up);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etPassword =((EditText)findViewById(R.id.editTextPasswordSignUp)).getText().toString();
                String etPasswordConfirm =((EditText)findViewById(R.id.editTextConfirmPassword)).getText().toString();
                String etEmail =((EditText)findViewById(R.id.editTextEmailSignUp)).getText().toString();

                // check data is not null
                if(etEmail==null||etPassword==null||etPasswordConfirm==null){
                    //toast message here
                    return;
                }
                //check confirm password
                if(!etPassword.equals(etPasswordConfirm)){
                    //toast message here
                    Toast toast=Toast.makeText(getApplicationContext(),"Password is not match",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Account account = new Account();
                account.account=etEmail;
                account.password=etPassword;

                //create an instance of the database
                Database db = Room.databaseBuilder(getApplicationContext(),Database.class,Database.Databasename)
                        .allowMainThreadQueries().build();
                AccountLayer accountLayer=db.accountDao();

                try{
                    accountLayer.insert(account);
                    Toast toast=Toast.makeText(getApplicationContext(),"Welcome",Toast.LENGTH_SHORT);
                    toast.show();
                }
                catch (Exception e){
                    Toast toast=Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

    }
}
