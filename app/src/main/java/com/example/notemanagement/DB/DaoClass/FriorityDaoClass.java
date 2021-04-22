package com.example.notemanagement.DB.DaoClass;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notemanagement.DB.EntityClass.FriorityModel;

import java.util.List;

@Dao
public interface FriorityDaoClass {
    @Insert
    void insertData(FriorityModel model);

    @Query("select * from Friorities")
    List<FriorityModel> getAllData();
}
