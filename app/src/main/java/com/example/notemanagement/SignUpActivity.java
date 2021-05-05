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

import com.example.notemanagement.DB.EntityClass.AccountModel;
import com.example.notemanagement.DB.DaoClass.AccountDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.extension.AlertDialogFragment;

public class SignUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
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
                boolean error=false;
                // check data is not null
                if(etEmail.length()==0){
                    ((EditText)findViewById(R.id.editTextEmailSignUp)).setError(getString(R.string.validate_email));
                    error=true;
                }
                if(etPassword.length()==0){
                    ((EditText)findViewById(R.id.editTextConfirmPassword)).setError(getString(R.string.validate_password));
                    error=true;
                }
                if(etPasswordConfirm.length()==0){
                    ((EditText)findViewById(R.id.editTextPasswordSignUp)).setError(getString(R.string.validate_password));
                    error=true;
                }
                if(error==true){
                    return;
                }

                // check validation email
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!etEmail.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    // or
                    return;
                }

                //check confirm password
                if(!etPassword.equals(etPasswordConfirm)){
                    AlertDialogFragment alert = new AlertDialogFragment(getString(R.string.signup_fail),
                            getString(R.string.password_notmatch));
                    alert.show(getSupportFragmentManager(),"sign up fail");
                    return;
                }
                Database db = Room.databaseBuilder(getApplicationContext(),Database.class,Database.Databasename)
                        .allowMainThreadQueries().build();
                AccountDaoClass accountLayer=db.accountDao();

                // check email exist
                AccountModel checkEmail = accountLayer.findEmail(etEmail);
                if(checkEmail!=null){
                    AlertDialogFragment alert = new AlertDialogFragment(getString(R.string.signup_fail),
                            getString(R.string.email_exist));
                    alert.show(getSupportFragmentManager(),"sign up fail");
                    return;
                }
                AccountModel account = new AccountModel(etEmail,etPassword,"","");
                //create an instance of the database
                try{
                    accountLayer.insert(account);
                    Toast toast=Toast.makeText(getApplicationContext(),"Sign up successfully",Toast.LENGTH_SHORT);
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