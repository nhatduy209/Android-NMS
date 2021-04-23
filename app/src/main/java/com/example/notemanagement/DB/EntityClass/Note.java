package com.example.notemanagement.DB.EntityClass;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Notes")
public class Note implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public  String category ;
    public  String priority ;
    public  String status ;
    public  String planDate ;
    public  String createdDate ;
    public int idAccount;

    @Ignore
    public Note(String name, String category, String priority, String status, String planDate, String createDate,int idAccount){
        this.name = name;
        this.category = category;
        this.priority = priority;
        this.status = status;
        this.planDate = planDate;
        this.createdDate = createDate;
        this.idAccount=idAccount;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }


    public  String getName()
    {
        return name;
    }
    public void setName(String Name) {
        this.name = Name;
    }

    public  String getCategory()
    {
        return category;
    }
    public void setCategory(String Category) {
        this.category = Category;
    }

    public  String getPriority()
    {
        return priority;
    }
    public void setPriority(String Priority) {
        this.priority = Priority;
    }

    public  String getStatus()
    {
        return status;
    }
    public void setStatus(String Status) {
        this.status = Status;
    }

    public  String getPlanDate()
    {
        return planDate;
    }
    public void setPlanDate(String PlanDate) {
        this.planDate = PlanDate;
    }

    public  String getCreateDate()
    {
        return createdDate;
    }
    public void setCreateDate(String CreateDate) {
        this.createdDate = CreateDate;
    }

    public  int getIdAccount()
    {
        return idAccount;
    }
    public void setIdAccount(int IdAccount) {
        this.idAccount = idAccount;
    }

    public Note(){
        this.name = "volleyball";
        this.category = "relax";
        this.priority ="low";
        this.status = "processing";
        this.planDate = "12/3/2000";
        this.createdDate = "12/3/2000 20:00:00";
        this.idAccount=1;
    }



}