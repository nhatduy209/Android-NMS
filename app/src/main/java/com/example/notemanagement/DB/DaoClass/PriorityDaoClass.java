package com.example.notemanagement.DB.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.PriorityModel;

import java.util.List;

@Dao
public interface PriorityDaoClass {
    @Insert
    void insertData(PriorityModel model);

    @Query("select * from Priorities WHERE idAccount=:idAccount")
    List<PriorityModel> getAllData(int idAccount);
    @Query("Select * from Priorities WHERE frName = :input")
    public PriorityModel getFr(String input);
    @Delete
    void deleteData(PriorityModel priorityModel);
    @Update
    public void updateData(PriorityModel priorityModel);
}
