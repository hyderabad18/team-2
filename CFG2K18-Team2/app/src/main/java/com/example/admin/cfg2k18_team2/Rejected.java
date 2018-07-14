package com.example.admin.cfg2k18_team2;

import android.os.Bundle;
import android.widget.FrameLayout;

/**
 * Created by admin on 7/14/2018.
 */

public class Rejected extends MainActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.recycler_view, contentFrameLayout);

    }
}
