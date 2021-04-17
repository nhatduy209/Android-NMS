package com.example.notemanagement.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Testing")
public class TestData implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;

    public int age;

}
