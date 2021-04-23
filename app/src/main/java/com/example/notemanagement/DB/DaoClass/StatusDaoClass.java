package com.example.notemanagement.DB.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.StatusModel;
import com.example.notemanagement.ui.status.StatusViewModel;

import java.util.List;

@Dao
public interface StatusDaoClass {
    @Insert
    void insertData(StatusModel model);

    @Query("select * from Statuses")
    List<StatusModel> getAllData();

    @Delete
    void deleteData(StatusModel statusModel);

    @Update
    public void updateData(StatusModel statusModel);
}
