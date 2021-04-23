package com.example.notemanagement.DB;


import android.text.style.UpdateAppearance;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AccountLayer {
    // get all
    @Query("SELECT * FROM Accounts")
    List<Account> getAll();

    @Insert
    public void insert(Account acc);

    @Delete
    public void delete(Account acc);

    @Update
    public void update(Account acc);
//    @Query("UPDATE Accounts SET email= :email, firstName=:acc.firstName,lastName=:acc.lastName WHERE idAccount=:acc.idAccount")
//    public void updateProfile(Account acc);

    // check account
    @Query("SELECT * FROM Accounts WHERE email= :email AND password=:password")
    Account findAccount(String email, String password);
}

