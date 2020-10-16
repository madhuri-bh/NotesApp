package com.example.NotesApp.addData;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import com.example.NotesApp.Notes;
import com.example.NotesApp.R;

public class AddActivity extends AppCompatActivity {

    private EditText editTitle, editContent;

    public static final String EXTRA_DATA_TITLE = "extra_notes_title";
    public static final String EXTRA_DATA_CONTENT = "extra_notes_content";
    public static final String EXTRA_DATA_ID = "extra_notes_id";

    public long id;

    private AddViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_activity);

        final Bundle extras = getIntent().getExtras();
        viewModel = new ViewModelProvider(this).get(AddViewModel.class);

        editTitle = findViewById(R.id.edit_title);
        editContent = findViewById(R.id.edit_content);
        Button saveButton = findViewById(R.id.button);

        if (extras != null) {
            String title = extras.getString(EXTRA_DATA_TITLE, "");
            String content = extras.getString(EXTRA_DATA_CONTENT, "");
            Long notesId = extras.getLong(EXTRA_DATA_ID, 0L);

            if (!title.isEmpty()) {
                editTitle.setText(title);
            }

            if (!content.isEmpty()) {
                editContent.setText(content);
            }

            saveButton.setText("UPDATE");
        }
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Title = editTitle.getText().toString();
                String Content = editContent.getText().toString();
                if (!Title.isEmpty() && !Content.isEmpty()) {
                    if (extras != null) {
                        long id = extras.getLong(EXTRA_DATA_ID);
                        Notes notes = new Notes(id, Title, Content);
                        viewModel.UpdateNotes(notes);
                    } else {
                        Notes notes = new Notes(id, Title, Content);
                        viewModel.InsertNotes(notes);
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Add title and content", Toast.LENGTH_SHORT).show();
                }

                setResult(RESULT_OK);
                finish();
            }

        });

    }
}


