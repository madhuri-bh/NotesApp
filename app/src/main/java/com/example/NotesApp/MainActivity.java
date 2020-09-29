package com.example.NotesApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;

import android.os.Bundle;

import com.example.NotesApp.addData.AddViewModel;

public class MainActivity extends AppCompatActivity {

    public AddViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(AddViewModel.class);
        viewModel.GetAllNotes().observe(this, new Observer<PagedList<Notes>>() {
            @Override
            public void onChanged(PagedList<Notes> notes) {

            }
        });
    }
}