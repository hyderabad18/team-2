
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

    public class AdminRequestBooks extends AppCompatActivity {

        private RecyclerView mRecyclerView;
        private RecyclerView.Adapter mAdapter;
        private RecyclerView.LayoutManager mLayoutManager;
        ArrayList<ReqBooks> list;
        private FirebaseAuth mAuth;
        private FirebaseAuth.AuthStateListener mAuthListner;
        private FirebaseDatabase mFirebaseDatabase;
        private DatabaseReference myref;
        ReqBooks reqBooks;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_admin_request_books);
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            myref =mFirebaseDatabase.getReference("users");
            reqBooks = new ArrayList<>();
            reqBooks = new ReqBooks();
            myref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot ds: dataSnapshot.getChildren())
                    {
                        reqBooks = ds.getValue(ReqBooks.class);
                        list.add(reqBooks);
                    }
                    mRecyclerView = (RecyclerView) findViewById(R.id.);

                    // use this setting to improve performance if you know that changes
                    // in content do not change the layout size of the RecyclerView
                    mRecyclerView.setHasFixedSize(true);

                    // use a linear layout manager
                    mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    mRecyclerView.setLayoutManager(mLayoutManager);

                    // specify an adapter (see also next example)
                    mAdapter = new Req(list);
                    mRecyclerView.setAdapter(mAdapter);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

}
