package com.example.notemanagement.DB.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Statuses")
public class StatusModel {
    @PrimaryKey(autoGenerate = true)
    private  int key;

    @ColumnInfo(name = "stName")
    private String name;

    @ColumnInfo(name = "stCrD")
    private String stCrD;

    @ColumnInfo(name = "idAccount")
    private int idAccount;

    public int getKey() {
        return key;
    }


    public String getName() {
        return name;
    }

    public String getStCrD() {
        return stCrD;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStCrD(String stCrD) {
        this.stCrD = stCrD;
    }
}
