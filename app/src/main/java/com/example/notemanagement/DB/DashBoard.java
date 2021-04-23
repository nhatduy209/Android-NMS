package com.example.notemanagement.DB;

public class DashBoard {
    String nameStatus;
    int count;

    public DashBoard(String nameStatus, int count) {
        this.nameStatus = nameStatus;
        this.count = count;
    }

    public String getStatus() {
        return nameStatus;
    }


    public int getCount() {
        return count;
    }
}
