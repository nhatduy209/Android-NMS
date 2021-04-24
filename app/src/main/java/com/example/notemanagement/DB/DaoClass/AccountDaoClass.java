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
    // get all
    @Query("SELECT * FROM AccountModel")
    List<AccountModel> getAll();

    @Insert
    public void insert(AccountModel acc);

    @Delete
    public void delete(AccountModel acc);

    @Update
    public void update(AccountModel acc);


    // check account
    @Query("SELECT * FROM AccountModel WHERE email= :email AND password=:password")
    AccountModel findAccount(String email, String password);

    @Query("SELECT * FROM AccountModel WHERE idAccount=:idAccount")
    AccountModel findAccountById(Integer idAccount);

    @Query("SELECT * FROM AccountModel WHERE email=:email")
    AccountModel findEmail(String email);
}
