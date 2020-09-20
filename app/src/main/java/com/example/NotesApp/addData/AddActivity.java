package com.example.NotesApp.addData;

import android.os.Bundle;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.NotesApp.R;

public class AddActivity extends AppCompatActivity {

    private EditText editTitle,editContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        editTitle = findViewById(R.id.edit_title);
        editTitle = findViewById(R.id.edit_content);

    }
}
