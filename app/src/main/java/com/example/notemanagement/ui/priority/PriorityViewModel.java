package com.example.notemanagement.ui.priority;

public class PriorityViewModel {
    private String priorityName;
    private String prorityCrD;

    public PriorityViewModel(String priorityName, String prorityCrD){
        this.priorityName = priorityName;
        this.prorityCrD = prorityCrD;
    }

    public  String getPriorityName(){
        return priorityName;
    }
    public void setPriorityName(){
        this.priorityName = priorityName;
    }

    public String getProrityCrD(){
        return prorityCrD;
    }
    public  void setProrityCrD(){
        this.prorityCrD = prorityCrD;
    }
}
