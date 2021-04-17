package com.example.notemanagement;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //handle event Sign Up
        signUp();
        //handle event Sign In
        signIn();
        //handle event Exit
        exit();
    }

    //set event click when click "Sign Up"
    public void signUp(){
        FloatingActionButton signUp=(FloatingActionButton)findViewById(R.id.floatingABSignUp);
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // set intent to start SignUpActivity
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
    //set event when click "Sign In"
    public void signIn(){
        Button signIn =(Button)findViewById(R.id.btn_sign_in);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignInActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
    //set event when click "Exit"
    public void exit(){
        Button exit=(Button)findViewById(R.id.btn_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // status:0 => everything OK
                // status:1 => quit cause error
                System.exit(0);
            }
        });
    }
}
