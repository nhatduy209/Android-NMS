package com.example.notemanagement.DB;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@androidx.room.Database(entities = {TestData.class} , version = 1 ,exportSchema = false)
public abstract class Database extends RoomDatabase {

    //create instance
    public static String Databasename = "TestingDatabase";
    public static  Database database;
    public abstract DAOLayer userDao();


    public synchronized static Database getInstance(Context context){
        //check if db exist or not
        if(database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),Database.class,Databasename)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
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
