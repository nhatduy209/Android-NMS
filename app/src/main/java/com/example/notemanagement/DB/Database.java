package com.example.notemanagement.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.notemanagement.DB.DaoClass.CategoryDaoClass;
import com.example.notemanagement.DB.DaoClass.FriorityDaoClass;
import com.example.notemanagement.DB.DaoClass.NoteDao;
import com.example.notemanagement.DB.DaoClass.StatusDaoClass;
import com.example.notemanagement.DB.EntityClass.CategoryModel;
import com.example.notemanagement.DB.EntityClass.FriorityModel;
import com.example.notemanagement.DB.EntityClass.Note;
import com.example.notemanagement.DB.EntityClass.StatusModel;

@androidx.room.Database(entities = { Account.class, StatusModel.class, FriorityModel.class, CategoryModel.class, Note.class} , version = 2 ,exportSchema = false)
public abstract class Database extends RoomDatabase {

    //create instance
    public static String Databasename = "TestingDatabase";
    public static  Database database;
    public abstract AccountLayer accountDao();
    public abstract StatusDaoClass statusDaoClass();
    public abstract FriorityDaoClass friorityDaoClass();
    public abstract CategoryDaoClass categoryDaoClass();
    public abstract NoteDao noteDao();

    public synchronized static Database getInstance(Context context){
        //check if db exist or not
        try{
        if(database == null){
            database = Room.databaseBuilder(context,Database.class,Databasename)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        }
        catch (Exception e ){
             Exception a = e;
       }


        return  database;
    }


    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }


}
