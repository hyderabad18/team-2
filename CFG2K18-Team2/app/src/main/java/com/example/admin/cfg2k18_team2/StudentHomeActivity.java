package com.example.admin.cfg2k18_team2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class StudentHomeActivity extends AppCompatActivity {

//    private RecyclerView mRecyclerView;
//    private RecyclerView.Adapter mAdapter;
//    private RecyclerView.LayoutManager mLayoutManager;
//    ArrayList<Books> list;
//    ArrayList<Books> list1;
//    private FirebaseAuth mAuth;
//    private FirebaseAuth.AuthStateListener mAuthListner;
//    private FirebaseDatabase mFirebaseDatabase;
//    private DatabaseReference myref;
//    Books books,books1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_student_home);
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        myref =mFirebaseDatabase.getReference("Books");
//        list = new ArrayList<>();
//        books = new Books();
//        SearchView searchview1;
//        searchview1 = findViewById(R.id.texttosearch);
//        searchview1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                if(newText != "")
//                {
//                    myref.addValueEventListener(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            for(DataSnapshot ds: dataSnapshot.getChildren())
//                            {
//                                books = ds.getValue(Books.class);
//                                String s=books.getTitle();
//                                if(s.contains(books.getTitle()))
//                                {
//                                    list.add(books);
//                                }
//                            }
//                            mRecyclerView = (RecyclerView) findViewById(R.id.booksrecycler);
//
//                            // use this setting to improve performance if you know that changes
//                            // in content do not change the layout size of the RecyclerView
//                            mRecyclerView.setHasFixedSize(true);
//
//                            // use a linear layout manager
//                            mLayoutManager = new LinearLayoutManager(getApplicationContext());
//                            mRecyclerView.setLayoutManager(mLayoutManager);
//
//                            // specify an adapter (see also next example)
//                            mAdapter = new StudentAdapter(list);
//                            mRecyclerView.setAdapter(mAdapter);
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//
//                        }
//                    });
//                }
//                return false;
//            }
//        });
//    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Toast.makeText(getApplicationContext(),"Student Logged In",Toast.LENGTH_LONG).show();
    }
}
