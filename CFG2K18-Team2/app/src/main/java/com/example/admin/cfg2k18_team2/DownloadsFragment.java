package com.example.admin.cfg2k18_team2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 7/14/2018.
 */

public class DownloadsFragment extends Fragment
{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<ApprovedDefault> def;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_home_student, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.rvList);
        linearLayoutManager = new LinearLayoutManager(rootView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DownloadsAdapter adapter = new DownloadsAdapter(def,rootView.getContext());
        recyclerView.setAdapter(adapter);


        return rootView;
    }



}
