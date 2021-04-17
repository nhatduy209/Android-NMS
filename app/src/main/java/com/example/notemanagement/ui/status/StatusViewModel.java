package com.example.notemanagement.ui.status;

import androidx.lifecycle.ViewModel;

public class StatusViewModel extends ViewModel {
//  declare the implements of model
    private String statusName;
    private String createdDate;
//  constructor
    public  StatusViewModel (String statusName, String createdDate){
        this.statusName = statusName;
        this.createdDate = createdDate;
    }
//  get method
    public  String getStatusName(){
        return statusName;
    }

    public String getCreatedDate(){
        return createdDate;
    }
//  set method
    public void setStatusName(){
        this.statusName = statusName;
    }

    public  void setCreatedDate(){
        this.createdDate = createdDate;
    }
}
