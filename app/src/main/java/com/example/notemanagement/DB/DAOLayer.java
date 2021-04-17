package com.example.notemanagement.DB;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOLayer {
    // get all
    @Query("SELECT * FROM Testing")
    List<TestData> getAll();
}
