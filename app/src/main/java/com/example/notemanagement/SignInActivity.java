package com.example.notemanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                String email =((EditText)findViewById(R.id.editTextEmail)).getText().toString();
                String password=((EditText)findViewById(R.id.editTextPassword)).getText().toString();

                Account currentAccount =accountLayer.findAccount(email,password);
                //sign in success
                if(currentAccount==null){
                    Toast toast = Toast.makeText(getApplicationContext(),"Sign in fail",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                // set session
                session.setEmail(currentAccount.email);
                session.setIdAccount(currentAccount.idAccount);
                session.setPassword(currentAccount.password);
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
