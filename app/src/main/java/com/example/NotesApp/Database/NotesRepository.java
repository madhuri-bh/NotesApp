package com.example.NotesApp.Database;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesRepository {
    private static NotesRepository notesRepository = null;
    private NotesDao notesDao;
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
}
