package com.example.notemanagement.DB.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notemanagement.DB.EntityClass.CategoryModel;

import java.util.List;

@Dao
public interface CategoryDaoClass {
    @Insert
    public void insertData(CategoryModel categoryModel);
    @Query("Select * from Categories")
    public List<CategoryModel> getAllData();
    @Delete
    public void deleteData(CategoryModel categoryModel);
    @Update
    public void updateData(CategoryModel categoryModel);
}
