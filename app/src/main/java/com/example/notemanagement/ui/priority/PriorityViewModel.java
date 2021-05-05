package com.example.notemanagement.ui.priority;

public class PriorityViewModel {
    private String friorityName;
    private String frorityCrD;

    public PriorityViewModel(String friorityName, String frorityCrD){
        this.friorityName = friorityName;
        this.frorityCrD = frorityCrD;
    }

    public  String getFriorityName(){
        return friorityName;
    }
    public void setFriorityName(){
        this.friorityName = friorityName;
    }

    public String getFrorityCrD(){
        return frorityCrD;
    }
    public  void setFrorityCrD(){
        this.frorityCrD = frorityCrD;
    }
}
