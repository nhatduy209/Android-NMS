package com.example.notemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        FloatingActionButton signUp=(FloatingActionButton)findViewById(R.id.floatingABSignUp);

        //set event click when click "Sign Up"
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                // set intent to start SignUpActivity
                Intent intent = new Intent(SignInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
    }


}
