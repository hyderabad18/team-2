package com.example.admin.cfg2k18_team2;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 7/15/2018.
 */
public class PendingAdapter extends RecyclerView.Adapter<PendingAdapter.MyViewHolder>
{
    private List<RequestVolunteers> def;

    RequestVolunteers adef;

    Context mContext;

    PendingAdapter(List<RequestVolunteers> def, Context mContext)
    {
        this.def=def;
        this.mContext = mContext;
    }

    @Override
    public PendingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_pending, parent, false);
        PendingAdapter.MyViewHolder myViewHolder = new PendingAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(PendingAdapter.MyViewHolder holder, int position)
    {
        adef = def.get(position);

        holder.tv1.setText(adef.getBookname());
        holder.tv2.setText(adef.getAuthorname());
        holder.tv3.setText(adef.getEdition());
        holder.tv4.setText(adef.getTags());

        holder.iv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(mContext,MediaPlayerActivity.class);
                i.putExtra("url",adef.getUrl());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return def.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1;
        TextView tv2;
        TextView tv3, tv4;
        ImageView iv1;


        public MyViewHolder(View itemView)
        {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.title);
            tv2 = (TextView) itemView.findViewById(R.id.author);
            tv3 = (TextView) itemView.findViewById(R.id.editionNumber);
            tv4 = (TextView) itemView.findViewById(R.id.tags);

            iv1=(ImageView)itemView.findViewById(R.id.play);
        }
    }


}
