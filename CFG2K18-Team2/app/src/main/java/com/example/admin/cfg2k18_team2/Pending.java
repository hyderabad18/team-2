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

public class Pending extends  MainActivity
{
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    List<RequestVolunteers> def;
    RequestVolunteers defau;

    FirebaseAuth auth;
    FirebaseUser user;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    DatabaseReference dref,dref2,dref3;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.recycler_view, contentFrameLayout);

        recyclerView = (RecyclerView) findViewById(R.id.rv);
        dref= FirebaseDatabase.getInstance().getReference();

        auth = FirebaseAuth.getInstance();


        user = auth.getCurrentUser();
        dref2 = dref.child("Volunteers").getRef();

        dref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                def=new ArrayList<RequestVolunteers>();
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                   // Toast.makeText(Pending.this," "+ds.child("email").get)
                    if(ds.child("email").getValue(String.class).toString().equals(user.getEmail().toString())){
                        DatabaseReference uploads=ds.child("Uploads").getRef();
                        uploads.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {

                                for(DataSnapshot ds:dataSnapshot.getChildren()){
                      //              Toast.makeText(Pending.this," "+ds.getValue(),Toast.LENGTH_LONG).show();
                                    defau=ds.getValue(RequestVolunteers.class);
                                    def.add(defau);
                                }
                                recyclerView = (RecyclerView) findViewById(R.id.rv);
                                linearLayoutManager = new LinearLayoutManager(getApplicationContext());
                                recyclerView.setLayoutManager(linearLayoutManager);
                                PendingAdapter adapter = new PendingAdapter(def,getApplicationContext());
                                recyclerView.setAdapter(adapter);


                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });


                    }

                }


                // dref2=dref.child("Volunteers");


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
