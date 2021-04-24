package com.example.notemanagement.DB.DaoClass;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.notemanagement.DB.EntityClass.NoteModel;

import java.util.List;

@Dao
public interface NoteDaoClass {
    @Insert
    public void insertNotes(NoteModel... notes);
    @Update
    public void updateNote(NoteModel note);
    @Delete
    public void deleteNotes(NoteModel note);
    @Query("SELECT * FROM NoteModel WHERE idAccount=:idAccount")
    List<NoteModel> getAll(int idAccount);
    @Query("SELECT * FROM NoteModel WHERE id = :id")
    NoteModel getNote(int id);

}
