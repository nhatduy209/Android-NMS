package com.example.notemanagement.extension;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context context) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setEmail(String email) {
        prefs.edit().putString("email", email).commit();
    }
    public void setPassword(String password){
        prefs.edit().putString("password",password).commit();
    }
    public void setIdAccount(Integer idAccount){
        prefs.edit().putInt("idAccount",idAccount).commit();
    }
    public void setIdNote(int idNote){
        prefs.edit().putInt("idNote",idNote).commit();
    }
    public String getEmail() {
        String usename = prefs.getString("email","");
        return usename;
    }
    public String getPassword(){
        String password=prefs.getString("password","");
        return password;
    }
    public int getIdAccount(){
        int idAccount=prefs.getInt("idAccount",0);
        return idAccount;
    }

    public void clear(){
        prefs.edit().clear().commit();
    }
}