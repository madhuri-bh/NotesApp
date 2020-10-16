package com.example.NotesApp.List;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.NotesApp.Database.NotesRepository;
import com.example.NotesApp.Notes;

public class MainViewModel extends AndroidViewModel {

    private NotesRepository notesRepository;
    public LiveData<PagedList<Notes>> pagedListLiveData;

    public MainViewModel(Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        pagedListLiveData = notesRepository.getAllNotes();
    }

    public void InsertNotes(Notes notes) {
        notesRepository.insertNotes(notes);
    }

    public void DeleteNotes(Notes notes) {
        notesRepository.DeleteNotes(notes);
    }

}
