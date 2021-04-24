package com.example.notemanagement.DB.EntityClass;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "AccountModel")
public class AccountModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idAccount;
    private  String password ;
    private  String email;
    private String firstName;
    private String lastName;

    public  int getIdAccount()
    {
        return idAccount;
    }
    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public  String getPassword()
    {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public  String getEmail()
    {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public  String getFirstName()
    {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public  String getLastName()
    {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AccountModel(){

    }
    public AccountModel(String email,String password, String firstName, String lastName){
        this.email=email;
        this.password=password;
        this.firstName=firstName;
        this.lastName=lastName;
    }
}
