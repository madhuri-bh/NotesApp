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
    public void InsertNotes(Notes notes);

    @Update
    public void UpdateNotes(Notes notes);

    @Delete
    public void DeleteNotes(Notes notes);

    @Query("SELECT * FROM Notes ORDER BY Id asc")
    DataSource.Factory<Integer,Notes> getAllNotes();

}
