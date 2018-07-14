package com.example.rohith.firebase;



import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ReqVolunteerAdapter extends RecyclerView.Adapter<ReqVolunteerAdapter.ViewHolder> {


    private ArrayList<Books> list;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView name,filename;
        Button accepVolun,rejectVolun;

        public ViewHolder(View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.namevolunteer);
            filename=(TextView)itemView.findViewById(R.id.filename);
            accepVolun =itemView.findViewById(R.id.acceptvolunteer);
            rejectVolun=itemView.findViewById(R.id.rejectvolunteer);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ReqVolunteerAdapter(ArrayList<Books> list1) {
        list = list1;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ReqVolunteerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_admin_request_volunteer, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getAuthor());
        holder.filename.setText(list.get(position).getTitle());
        holder.accepVolun.setText(list.get(position).getEdition());
        holder.rejectVolun.setText(list.get(position).newTags);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return list.size();
    }
}

