package com.example.notemanagement.ui.friority;

public class FriorityViewModel {
    private String friorityName;
    private String frorityCrD;

    public FriorityViewModel(String friorityName, String frorityCrD){
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
