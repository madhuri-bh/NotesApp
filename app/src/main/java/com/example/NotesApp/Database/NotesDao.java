package com.example.NotesApp.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.NotesApp.Notes;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM Notes ORDER BY Id asc")
    LiveData<Notes> getAlNotes();

    @Insert
    public void InsertNotes(Notes notes);

    @Update
    public void UpdateNotes(Notes notes);

    @Delete
    public void DeleteNotes(Notes notes);

}
