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
 * Created by admin on 7/14/2018.
 */

public class ApprovedAdapter extends RecyclerView.Adapter<ApprovedAdapter.MyViewHolder>
{
    private List<ApprovedDefault> def;

    ApprovedDefault adef;

    Context mContext;

    ApprovedAdapter(List<ApprovedDefault> def, Context mContext)
    {
        this.def=def;
        this.mContext = mContext;
    }

    @Override
    public ApprovedAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.approved_card, parent, false);
        ApprovedAdapter.MyViewHolder myViewHolder = new ApprovedAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(ApprovedAdapter.MyViewHolder holder, int position)
    {
        adef = def.get(position);

        holder.tv1.setText(adef.getTitle());
        holder.tv2.setText(adef.getAuthor());
        holder.tv3.setText(adef.getEdition());
        holder.tv4.setText(adef.getNewTags());
        holder.tv5.setText(adef.getDownloadcount()+"");
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
        return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv1;
        TextView tv2;
        TextView tv3, tv4,tv5;
        ImageView iv1;


        public MyViewHolder(View itemView)
        {
            super(itemView);

            tv1 = (TextView) itemView.findViewById(R.id.title);
            tv2 = (TextView) itemView.findViewById(R.id.author);
            tv3 = (TextView) itemView.findViewById(R.id.editionNumber);
            tv4 = (TextView) itemView.findViewById(R.id.AddNewTags);
            tv5 = (TextView) itemView.findViewById(R.id.countDownloads);
            iv1=(ImageView)itemView.findViewById(R.id.play);
        }
    }
}
