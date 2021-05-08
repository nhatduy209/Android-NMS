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
    Database db;
    AccountDaoClass accountLayer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        db = Database.getInstance(getApplicationContext());
        accountLayer=db.accountDao();

        //handle event Sign Up
        signUp();
        //handle event Sign In
        signIn();
    }
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
                    ((EditText)findViewById(R.id.editTextPasswordSignUp)).setError(getString(R.string.validate_password));
                    error=true;
                }
                if(etPasswordConfirm.length()==0){
                    ((EditText)findViewById(R.id.editTextConfirmPassword)).setError(getString(R.string.validate_password));
                    error=true;
                }
                // check validation email
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!etEmail.matches(emailPattern) && etEmail.length()>0)
                {
                    ((EditText)findViewById(R.id.editTextEmailSignUp)).setError("Invalid email address");
                    error=true;
                }
                //check strong password
                if(StrongPassword(etPassword)==false){
                    ((EditText)findViewById(R.id.editTextPasswordSignUp)).setError(getString(R.string.msg_strong_password));
                    error=true;
                }
                if(error==true){
                    return;
                }

                //check confirm password
                if(!etPassword.equals(etPasswordConfirm) && etPassword.length()>0){
                    AlertDialogFragment alert = new AlertDialogFragment(getString(R.string.tt_signup_fail),
                            getString(R.string.msg_password_notmatch));
                    alert.show(getSupportFragmentManager(),"sign up fail");
                    return;
                }

                // check email exist
                AccountModel checkEmail = accountLayer.findEmail(etEmail);
                if(checkEmail!=null){
                    AlertDialogFragment alert = new AlertDialogFragment(getString(R.string.tt_signup_fail),
                            getString(R.string.msg_email_exist));
                    alert.show(getSupportFragmentManager(),"sign up fail");
                    return;
                }
                AccountModel account = new AccountModel(etEmail,etPassword,"","");
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
    public boolean StrongPassword(String password){
        boolean sawUpper = false;
        boolean sawLower = false;
        boolean sawDigit = false;
        boolean sawSpecial = false;

        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if(!sawSpecial && !Character.isLetterOrDigit(c)){
                sawSpecial = true;
            }
            else{
                if(!sawDigit&& Character.isDigit(c)){
                    sawDigit = true;
                }
                else{
                    if(!sawUpper&& Character.isUpperCase(c)){
                        sawUpper=true;
                    }
                    else if(!sawLower &&Character.isLowerCase(c)){
                        sawLower=true;
                    }
                }
            }
        }
        if(sawDigit&& sawLower&& sawSpecial && sawUpper ){
            return true;
        }
        return false;
    }
}