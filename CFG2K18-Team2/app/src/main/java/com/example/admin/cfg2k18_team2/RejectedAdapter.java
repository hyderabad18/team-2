package com.example.admin.cfg2k18_team2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 7/14/2018.
 */

public class RejectedAdapter extends RecyclerView.Adapter<RejectedAdapter.MyViewHolder>
{

    private List<RejectedDefault> def;

    RejectedDefault adef;

    Context mContext;

    RejectedAdapter(List<RejectedDefault> def, Context mContext)
    {
        this.def=def;
        this.mContext = mContext;
    }

    @Override
    public RejectedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rejected_card, parent, false);
        RejectedAdapter.MyViewHolder myViewHolder = new RejectedAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(RejectedAdapter.MyViewHolder holder, int position)
    {
        adef = def.get(position);

        holder.tv1.setText(adef.getTitle());
        holder.tv2.setText(adef.getAuthor());
        holder.tv3.setText(adef.getEdition());
        holder.tv4.setText(adef.getComment());

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


        public MyViewHolder(View itemView)
        {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.title);
            tv2 = (TextView) itemView.findViewById(R.id.author);
            tv3 = (TextView) itemView.findViewById(R.id.editionNumber);
            tv4 = (TextView) itemView.findViewById(R.id.commentContent);
        }
    }

}
