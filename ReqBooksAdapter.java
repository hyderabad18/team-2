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

public class ReqBooksAdapter extends RecyclerView.Adapter<ReqBooksAdapter.ViewHolder> {


    private ArrayList<ReqBooks> list;


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,text;

        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.namerequesteduser;
            text=(TextView)itemView.findViewById(R.id.requestedtext);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReqBooksAdapter(ArrayList<ReqBooks> list1) {
        list = list1;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReqBooksAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request_books_adapter, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getEmail());
        holder.text.setText(list.get(position).getPassword());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
