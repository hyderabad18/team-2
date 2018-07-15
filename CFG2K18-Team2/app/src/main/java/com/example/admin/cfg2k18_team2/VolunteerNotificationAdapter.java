package com.example.admin.cfg2k18_team2;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by admin on 7/15/2018.
 */

public class VolunteerNotificationAdapter extends RecyclerView.Adapter<VolunteerNotificationAdapter.MyViewHolder>
{
    private List<ApprovedDefault> def;

    ApprovedDefault adef;

    Context mContext;

    VolunteerNotificationAdapter(List<ApprovedDefault> def, Context mContext)
    {
        this.def=def;
        this.mContext = mContext;
    }

    @Override
    public VolunteerNotificationAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_notifications, parent, false);
        VolunteerNotificationAdapter.MyViewHolder myViewHolder = new VolunteerNotificationAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(VolunteerNotificationAdapter.MyViewHolder holder, int position)
    {
        adef = def.get(position);

        holder.tv1.setText(adef.getVolunteername());
        holder.tv2.setText(adef.getTitle());
        holder.tv3.setText(adef.getAuthor());
        holder.tv4.setText(adef.getEdition());
    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1;
        TextView tv2;
        TextView tv3, tv4;


        public MyViewHolder(View itemView)
        {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.VolunteerName);
            tv2 = (TextView) itemView.findViewById(R.id.title);
            tv3 = (TextView) itemView.findViewById(R.id.author);
            tv4 = (TextView) itemView.findViewById(R.id.editionNumber);

        }
    }
}
