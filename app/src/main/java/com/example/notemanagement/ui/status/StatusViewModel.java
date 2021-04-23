package com.example.notemanagement.ui.status;

public class StatusViewModel{
//  declare the implements of model
    private String statusName;
    private String createdDate;
//  constructor
    public  StatusViewModel (String statusName, String createdDate){
        this.statusName = statusName;
        this.createdDate = createdDate;
    }

    public  String getStatusName(){
        return statusName;
    }
    public void setStatusName(){
        this.statusName = statusName;
    }

    public String getCreatedDate(){
        return createdDate;
    }
    public  void setCreatedDate(){
        this.createdDate = createdDate;
    }
}
