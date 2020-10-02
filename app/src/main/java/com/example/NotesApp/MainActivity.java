package com.example.NotesApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.NotesApp.List.NotesListPagingAdapter;
import com.example.NotesApp.addData.AddViewModel;

public class MainActivity extends AppCompatActivity {

    public AddViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        final NotesListPagingAdapter notesListPagingAdapter = new NotesListPagingAdapter();
        recyclerView.setAdapter(notesListPagingAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.GetAllNotes().observe(this, new Observer<PagedList<Notes>>() {
            @Override
            public void onChanged(PagedList<Notes> notes) {
notesListPagingAdapter.submitList(notes);
            }
        });
    }
}