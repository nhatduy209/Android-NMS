package com.example.notemanagement;

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
        prefs.edit().putInt("idAccount",idNote).commit();
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
        Integer idAccount=prefs.getInt("idAccount",0);
        return idAccount;
    }
    public int getIdNote(){
        int idNote=prefs.getInt("idNote",0);
        return idNote;
    }
    public void clear(){
        prefs.edit().clear();
    }
}