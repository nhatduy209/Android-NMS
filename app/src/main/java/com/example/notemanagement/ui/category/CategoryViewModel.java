package com.example.notemanagement.ui.category;

public class CategoryViewModel {
    //  declare the implements of model
    private String categoryName;
    private String categoryCrD;
    //  constructor
    public CategoryViewModel(String categoryName, String categoryCrD){
        this.categoryName = categoryName;
        this.categoryCrD = categoryCrD;
    }

    public  String getCategoryName(){
        return categoryName;
    }
    public void setCategoryName(){
        this.categoryName = categoryName;
    }

    public String getCategoryCrD(){
        return categoryCrD;
    }
    public  void setCategoryCrD(){
        this.categoryCrD = categoryCrD;
    }
}
