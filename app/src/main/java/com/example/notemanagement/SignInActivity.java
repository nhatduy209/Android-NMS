package com.example.notemanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.notemanagement.DB.DaoClass.AccountDaoClass;
import com.example.notemanagement.DB.Database;
import com.example.notemanagement.DB.EntityClass.AccountModel;
import com.example.notemanagement.extension.AlertDialogFragment;
import com.example.notemanagement.extension.Session;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SignInActivity extends AppCompatActivity  {
    Database db;
    AccountDaoClass accountLayer;
    private  Session session;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private EditText emailRemember , passwordRemember ;
    private CheckBox rememberMe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        //build database
        db = Database.getInstance(getApplicationContext());
        accountLayer=db.accountDao();
        // call session
        session= new Session(getApplicationContext());

        // remember me
        rememberMe = (CheckBox)findViewById(R.id.checkBoxRememberMe);
        emailRemember =((EditText)findViewById(R.id.editTextEmail));
        passwordRemember=((EditText)findViewById(R.id.editTextPassword));
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();
        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {
            emailRemember.setText(loginPreferences.getString("username", ""));
            passwordRemember.setText(loginPreferences.getString("password", ""));
            rememberMe.setChecked(true);
        }


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
                boolean error=false;
                String email =((EditText)findViewById(R.id.editTextEmail)).getText().toString();
                if(email.length()==0){
                    ((EditText) findViewById(R.id.editTextEmail)).setError(getString(R.string.validate_email));
                    error=true;
                }
                String password=((EditText)findViewById(R.id.editTextPassword)).getText().toString();
                if(password.length()==0){
                    ((EditText) findViewById(R.id.editTextPassword)).setError(getString(R.string.validate_password));
                    error=true;
                }
                if(error==true){
                    return;
                }
                // check validation email
                String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                if (!email.matches(emailPattern))
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address",Toast.LENGTH_SHORT).show();
                    // or
                    return;
                }
                AccountModel currentAccount =accountLayer.findAccount(email,password);

                //sign in success
                if(currentAccount==null){
                    AlertDialogFragment alert = new AlertDialogFragment("Login fail",
                            "Email or password incorrect. Please try logging in again ");
                    alert.show(getSupportFragmentManager(), "login_fail");
                    return;
                }

                // handle remember me
                if (rememberMe.isChecked()) {
                    loginPrefsEditor.putBoolean("saveLogin", true);
                    loginPrefsEditor.putString("username", email);
                    loginPrefsEditor.putString("password", password);
                } else {
                    loginPrefsEditor.clear();
                }
                loginPrefsEditor.commit();

                // set session
                session.setEmail(currentAccount.getEmail());
                session.setIdAccount(currentAccount.getIdAccount());
                session.setPassword(currentAccount.getPassword());

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