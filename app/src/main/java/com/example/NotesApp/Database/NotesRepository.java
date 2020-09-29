package com.example.NotesApp.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.NotesApp.Notes;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepository {
    private static NotesRepository notesRepository = null;
    private NotesDao notesDao;
    private static int PAGE_SIZE = 15;
    private ExecutorService executors = Executors.newSingleThreadExecutor();

    public NotesRepository(Application application) {
        NotesDatabase database = NotesDatabase.getInstance(application);
        NotesDao dao = database.notesDao();
    }

    public NotesRepository getNotesRepository(Application application) {
        if (notesRepository == null) {
            synchronized (NotesRepository.class) {

                if (notesRepository == null) {
                    notesRepository = new NotesRepository(application);
                }
            }
        }
        return notesRepository;
    }
    public void insertNotes(final Notes notes) {
executors.execute(new Runnable() {
    @Override
    public void run() {
        notesDao.InsertNotes(notes);
    }
});
    }

    public void updateNotes(final Notes notes) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.UpdateNotes(notes);
            }
        });
    }

    public void DeleteNotes(final Notes notes) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                notesDao.DeleteNotes(notes);
            }
        });
    }

    public LiveData<PagedList<Notes>> getAllNotes() {
        return new LivePagedListBuilder<>(
                notesDao.getAllNotes(), PAGE_SIZE
        ).build();
    }
}
