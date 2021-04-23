package com.example.notemanagement.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Insert
    public void insertNotes(Note... notes);
    @Update
    public void updateNote(Note note);
    @Delete
    public void deleteNotes(Note note);
    @Query("SELECT * FROM Notes")
    List<Note> getAll();
    @Query("SELECT * FROM Notes WHERE id = :id")
    Note getNote(int id);

}
