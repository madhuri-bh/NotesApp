package com.example.NotesApp.Database;

import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.NotesApp.Notes;

@Dao
public interface NotesDao {

    @Insert
    void InsertNotes(Notes notes);

    @Update
    void UpdateNotes(Notes notes);

    @Delete
    void DeleteNotes(Notes notes);

    @Query("SELECT * FROM Notes ORDER BY Title asc")
    DataSource.Factory<Integer, Notes> getAllNotes();

}
