package com.example.rohith.firebase;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {


    private ArrayList<Books> list;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView aname,auname,edition,tags;

        public ViewHolder(View itemView) {
            super(itemView);
            aname=(TextView)itemView.findViewById(R.id.audioname);
            auname=(TextView)itemView.findViewById(R.id.authorname);
            edition=(TextView)itemView.findViewById(R.id.edition);
            tags=(TextView)itemView.findViewById(R.id.tags);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public StudentAdapter(ArrayList<Books> list1) {
        list = list1;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_search_books, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.auname.setText(list.get(position).getAuthor());
        holder.aname.setText(list.get(position).getTitle());
        holder.edition.setText(list.get(position).getEdition());
        holder.tags.setText(list.get(position).newTags);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}

