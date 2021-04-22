package com.example.notemanagement.DB.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Categories")
public class CategoryModel {
    @PrimaryKey(autoGenerate = true)
    private  int key;

    @ColumnInfo(name = "cName")
    private String name;

    @ColumnInfo(name = "CatCrD")
    private String CatCrD;

    @ColumnInfo(name = "idAccount")
    private String idAccount;

    public int getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getCatCrD() {
        return CatCrD;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCatCrD(String CatCrD) {
        this.CatCrD = CatCrD;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }
}
