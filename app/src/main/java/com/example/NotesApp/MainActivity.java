package com.example.NotesApp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.NotesApp.List.MainViewModel;
import com.example.NotesApp.List.NotesListPagingAdapter;
import com.example.NotesApp.addData.AddActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_DATA_REQUEST_CODE = 1;
    public static final int NEW_UPDATE_REQUEST_CODE = 2;

    public static final String EXTRA_DATA_TITLE = "extra_notes_title";
    public static final String EXTRA_DATA_CONTENT = "extra_notes_content";
    public static final String EXTRA_DATA_ID = "extra_notes_id";

    private Notes notes;
    public MainViewModel viewModel;
    public FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final NotesListPagingAdapter notesListPagingAdapter = new NotesListPagingAdapter();
        recyclerView.setAdapter(notesListPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel.pagedListLiveData.observe(this, new Observer<PagedList<Notes>>() {
            @Override
            public void onChanged(PagedList<Notes> notes) {
                notesListPagingAdapter.submitList(notes);
            }
        });

        floatingActionButton = findViewById(R.id.button_add_note);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivityForResult(intent, NEW_DATA_REQUEST_CODE);
            }
        });

        final ConstraintLayout constraintLayout = findViewById(R.id.constraint_layout);
        final Snackbar snackbar = Snackbar.make(constraintLayout, "Note Deleted", BaseTransientBottomBar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewModel.InsertNotes(notes);
                    }
                });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                notes = notesListPagingAdapter.getNotesAtPosition(position);
                viewModel.DeleteNotes(notes);
                snackbar.show();
            }
        });

        itemTouchHelper.attachToRecyclerView(recyclerView);

        notesListPagingAdapter.setOnItemClickListener(new NotesListPagingAdapter.ClickListener() {
            @Override
            public void itemClick(int position, View view) {
                Notes currentNotes = notesListPagingAdapter.getNotesAtPosition(position);
                launchUpdateNotesActivity(currentNotes);
            }
        });

    }

    public void launchUpdateNotesActivity(Notes currentNotes) {
        Intent intent = new Intent(MainActivity.this, AddActivity.class);
        intent.putExtra(EXTRA_DATA_TITLE, currentNotes.getTitle());
        intent.putExtra(EXTRA_DATA_CONTENT, currentNotes.getContent());
        intent.putExtra((EXTRA_DATA_ID), currentNotes.getId());
        startActivityForResult(intent, NEW_UPDATE_REQUEST_CODE);
    }
}