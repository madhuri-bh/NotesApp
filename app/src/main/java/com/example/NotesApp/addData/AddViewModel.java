package com.example.NotesApp.addData;
import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.example.NotesApp.Database.NotesRepository;
import com.example.NotesApp.Notes;

public class AddViewModel extends AndroidViewModel {

    private NotesRepository notesRepository;
    private LiveData<PagedList<Notes>> pagedListLiveData;

    public AddViewModel(@NonNull Application application) {
        super(application);
        notesRepository = new NotesRepository(application);
        pagedListLiveData = notesRepository.getAllNotes();
    }

    public void InsertNotes(Notes notes) {
        notesRepository.insertNotes(notes);
    }

    public void UpdateNotes(Notes notes) {
        notesRepository.updateNotes(notes);
    }

    public void DeleteNotes(Notes notes) {
        notesRepository.DeleteNotes(notes);
    }

    public LiveData<PagedList<Notes>> GetAllNotes(){
        return pagedListLiveData;
    }
}
