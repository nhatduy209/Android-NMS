package com.example.notemanagement.DB.EntityClass;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Friorities")
public class FriorityModel {
    @PrimaryKey(autoGenerate = true)
    private  int key;

    @ColumnInfo(name = "frName")
    private String name;

    @ColumnInfo(name = "frCrD")
    private String frCrD;

    @ColumnInfo(name = "idAccount")
    private String idAccount;

    public int getKey() {
        return key;
    }


    public String getName() {
        return name;
    }

    public String getFrCrD() {
        return frCrD;
    }

    public String getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(String idAccount) {
        this.idAccount = idAccount;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFrCrD(String frCrD) {
        this.frCrD = frCrD;
    }
}
