package com.example.NotesApp.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.example.NotesApp.Notes;
import com.example.NotesApp.R;

public class NotesListPagingAdapter extends PagedListAdapter<Notes, ViewHolder> {

    private ClickListener clickListener;

    public NotesListPagingAdapter() {
        super(DIFFUTIL_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Notes currentNotes = getItem(position);
        if (currentNotes != null) {
            holder.bind(currentNotes);
            if (clickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickListener.itemClick(position, view);
                    }
                });
            }
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public interface ClickListener {
        void itemClick(int position, View view);
    }

    public Notes getNotesAtPosition(int position) {
        return getItem(position);
    }

    private static DiffUtil.ItemCallback<Notes> DIFFUTIL_CALLBACK = new DiffUtil.ItemCallback<Notes>() {
        @Override
        public boolean areItemsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return (oldItem.getTitle().equals(newItem.getTitle()));
        }

        @Override
        public boolean areContentsTheSame(@NonNull Notes oldItem, @NonNull Notes newItem) {
            return oldItem.isNotesEqual(newItem);
        }
    };
}
