package com.example.rohith.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;

public class AdminRequestVolunteers extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<RequestVolunteers> list;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private DatabaseReference mFirebaseDatabase;
    private DatabaseReference myref;
    RequestVolunteers reqvolunteers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_request_volunteers);
        mFirebaseDatabase = FirebaseDatabase.getInstance().getReference();
        myref =mFirebaseDatabase.child("Volunteers");

        reqvolunteers = new RequestVolunteers();
        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    DatabaseReference uploads=ds.child("Uploads").getRef();
                    uploads.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            list = new ArrayList<RequestVolunteers>();
                            for (DataSnapshot ds:dataSnapshot.getChildren()){
                                reqvolunteers = ds.getValue(RequestVolunteers.class);
                                list.add(reqvolunteers);

                            }
                            mRecyclerView = (RecyclerView) findViewById(R.id.requestvolunteers);

                            // use this setting to improve performance if you know that changes
                            // in content do not change the layout size of the RecyclerView
                            mRecyclerView.setHasFixedSize(true);

                            // use a linear layout manager
                            mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            mRecyclerView.setLayoutManager(mLayoutManager);

                            // specify an adapter (see also next example)
                            mAdapter = new ReqVolunteerAdapter(list,AdminRequestVolunteers.this);
                            mRecyclerView.setAdapter(mAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
