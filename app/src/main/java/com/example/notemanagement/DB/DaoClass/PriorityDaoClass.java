package com.example.notemanagement.DB.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notemanagement.DB.EntityClass.FriorityModel;

import java.util.List;

@Dao
public interface PriorityDaoClass {
    @Insert
    void insertData(FriorityModel model);

    @Query("select * from Friorities WHERE idAccount=:idAccount")
    List<FriorityModel> getAllData(int idAccount);
    @Delete
    void deleteData(FriorityModel friorityModel);
    @Update
    public void updateData(FriorityModel friorityModel);
}
