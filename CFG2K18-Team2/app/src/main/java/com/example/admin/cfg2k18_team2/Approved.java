package com.example.admin.cfg2k18_team2;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by admin on 7/14/2018.
 */

public class Approved extends MainActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<RejectedDefault> def;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        recyclerView = (RecyclerView) findViewById(R.id.rv);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.recycler_view, contentFrameLayout);


        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        RejectedAdapter adapter = new RejectedAdapter(def,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}
