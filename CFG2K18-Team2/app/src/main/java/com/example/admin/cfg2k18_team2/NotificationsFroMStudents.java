package com.example.admin.cfg2k18_team2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * Created by admin on 7/15/2018.
 */

public class NotificationsFroMStudents extends Fragment
{

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<ReqBooks> list;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference myref;
    private Button books,volunteers,disable,dashboard;
    ReqBooks reqBooks;

    public NotificationsFroMStudents() {
    }

    FirebaseAuth auth;
    DatabaseReference rootAdminref;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.list_notifications,container,false);

        myref=mFirebaseDatabase.child("Students").getRef();


        mRecyclerView=(RecyclerView)rootView.findViewById(R.id.rvNotifications);

        //  reqBooks = new com.example.rohith.firebase.ReqBooks();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                list=new ArrayList<ReqBooks>();
                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    DatabaseReference request=ds.child("Requests").getRef();
                    request.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for (DataSnapshot ds:dataSnapshot.getChildren())
                            {
                                reqBooks = ds.getValue(ReqBooks.class);
                                list.add(reqBooks);

                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }


                // use this setting to improve performance if you know that changes
                // in content do not change the layout size of the RecyclerView
                mRecyclerView.setHasFixedSize(true);

                // use a linear layout manager
                mLayoutManager = new LinearLayoutManager(getContext());
                mRecyclerView.setLayoutManager(mLayoutManager);

                // specify an adapter (see also next example)
                mAdapter = new ReqBooksAdapter(list,getContext());
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        return rootView;
    }

    @Override
    public String toString() {
        return "Student Requests";
    }
}
