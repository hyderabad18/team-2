package com.example.admin.cfg2k18_team2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 7/14/2018.
 */

public class Approved extends MainActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<ApprovedDefault> def;
    ApprovedDefault defau;
    DatabaseReference dref,dref2,dref3;

    FirebaseAuth auth;
    FirebaseUser user;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String email,key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        dref= FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();

        user = auth.getCurrentUser();

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.recycler_view, contentFrameLayout);

//        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
//        editor=sharedPreferences.edit();
//
//        if(sharedPreferences!=null){
//            key=sharedPreferences.getString("key","");
//            email=sharedPreferences.getString("email","");
//        }
        dref2 = dref.child("Volunteers").getRef();
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        dref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                def=new ArrayList<ApprovedDefault>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){

                    DatabaseReference uploads=ds.child("Approved").getRef();
                    uploads.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                                defau=ds.getValue(ApprovedDefault.class);
                                def.add(defau);
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(linearLayoutManager);
                ApprovedAdapter adapter = new ApprovedAdapter(def,getApplicationContext());
                recyclerView.setAdapter(adapter);

               // dref2=dref.child("Volunteers");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.rv);

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        ApprovedAdapter adapter = new ApprovedAdapter(def,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }
}
