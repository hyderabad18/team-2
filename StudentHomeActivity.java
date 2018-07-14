package com.example.rohith.firebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class StudentHomeActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<Books> list;
    ArrayList<Books> list1;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListner;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myref;
    Books books,books1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myref =mFirebaseDatabase.getReference("Books");
        list = new ArrayList<>();
        books = new Books();
        SearchView searchview1;
        searchview1 = findViewById(R.id.texttosearch);
        searchview1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText != "")
                {
                    myref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot ds: dataSnapshot.getChildren())
                            {
                                books = ds.getValue(Books.class);
                                String s=books.getTitle();
                                if(s.contains(books.getTitle()))
                                {
                                    list.add(books);
                                }
                            }
                            mRecyclerView = (RecyclerView) findViewById(R.id.booksrecycler);

                            // use this setting to improve performance if you know that changes
                            // in content do not change the layout size of the RecyclerView
                            mRecyclerView.setHasFixedSize(true);

                            // use a linear layout manager
                            mLayoutManager = new LinearLayoutManager(getApplicationContext());
                            mRecyclerView.setLayoutManager(mLayoutManager);

                            // specify an adapter (see also next example)
                            mAdapter = new StudentAdapter(list);
                            mRecyclerView.setAdapter(mAdapter);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                return false;
            }
        });
    }
}
