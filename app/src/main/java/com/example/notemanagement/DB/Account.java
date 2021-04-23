package com.example.notemanagement.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity(tableName = "Accounts")
public class Account implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int idAccount;

    public String account;

    public  String password ;
}
