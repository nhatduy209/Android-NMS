package com.example.notemanagement.DB.DaoClass;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notemanagement.DB.EntityClass.AccountModel;

import java.util.List;

@Dao
public interface AccountDaoClass {

    @Insert
    void insert(AccountModel acc);

    @Delete
    void delete(AccountModel acc);

    @Update
    void update(AccountModel acc);

    // check account
    @Query("SELECT * FROM AccountModel WHERE email= :email AND password=:password")
    AccountModel findAccount(String email, String password);

    @Query("SELECT * FROM AccountModel WHERE email=:email")
    AccountModel findEmail(String email);
}
