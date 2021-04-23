package com.example.notemanagement.ui.note;

public class NoteModel {
    private String Name;
    private String Category;
    private String Priority;
    private String Status;
    private String PlanDate;
    private String CreateDate;

    public NoteModel(String name, String category, String priority, String status, String planDate, String createDate){
        this.Name = name;
        this.Category = category;
        this.Priority = priority;
        this.Status = status;
        this.PlanDate = planDate;
        this.CreateDate = createDate;
    }

    public  String getName()
    {
        return Name;
    }
    public void setName(String Name) {
        this.Name = Name;
    }

    public  String getCategory()
    {
        return Category;
    }
    public void setCategory(String Category) {
        this.Category = Category;
    }

    public  String getPriority()
    {
        return Priority;
    }
    public void setPriority(String Priority) {
        this.Priority = Priority;
    }

    public  String getStatus()
    {
        return Status;
    }
    public void setStatus(String Status) {
        this.Status = Status;
    }

    public  String getPlanDate()
    {
        return PlanDate;
    }
    public void setPlanDate(String PlanDate) {
        this.PlanDate = PlanDate;
    }

    public  String getCreateDate()
    {
        return CreateDate;
    }
    public void setCreateDate(String CreateDate) {
        this.CreateDate = CreateDate;
    }

}
