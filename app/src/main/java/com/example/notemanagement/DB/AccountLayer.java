package com.example.notemanagement.DB;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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
    
}

