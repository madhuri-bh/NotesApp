package com.example.NotesApp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {

    private TextView title,content;


    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        content = itemView.findViewById(R.id.content);
    }

    public void bind(Notes notes) {
        title.setText(notes.getTitle());
        content.setText(notes.getContent());
    }


}
