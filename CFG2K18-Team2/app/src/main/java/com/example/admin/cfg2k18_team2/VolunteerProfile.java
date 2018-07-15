package com.example.admin.cfg2k18_team2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by admin on 7/14/2018.
 */

public class VolunteerProfile extends MainActivity
{
    EditText name, phone , skills,email;
    Button done;
    String namee,phonee,skillss,emailid;
    DatabaseReference dref, dref2, dref3;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.volunteer_profile, contentFrameLayout);


        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=sharedPreferences.edit();

        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        skills = (EditText) findViewById(R.id.skills);
        email=(EditText)findViewById(R.id.emailtxt);
        done = (Button) findViewById(R.id.done);
        if(sharedPreferences!=null){
            key=sharedPreferences.getString("key","");;
          Toast.makeText(getApplicationContext(),""+key,Toast.LENGTH_SHORT).show();
        }


        dref = FirebaseDatabase.getInstance().getReference();
        dref2=dref.child("Volunteers").getRef();
        //dref3 = dref2.child(key).getRef();


        dref3=dref2.child(key).getRef();
        dref3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                name.setText(""+dataSnapshot.child("name").getValue(String.class));
                email.setText(""+dataSnapshot.child("email").getValue(String.class));
                phone.setText(""+dataSnapshot.child("mobile").getValue(String.class));
                DatabaseReference skill=dataSnapshot.child("skills").getRef();
                skill.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                         String sk="";
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            sk+=ds.getValue(String.class);
                        }
                        skills.setText(sk);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        final Button addskill=(Button)findViewById(R.id.addskill);
        final EditText ed=(EditText)findViewById(R.id.moreskill);
        addskill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ed.setVisibility(View.VISIBLE);
                addskill.setVisibility(View.GONE);
            }
        });


        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                namee = name.getText().toString();
                phonee = phone.getText().toString();
                skillss= skills.getText().toString();


                dref3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        dataSnapshot.child("name").getRef().setValue(namee);
                        dataSnapshot.child("email").getRef().setValue(email);
                        dataSnapshot.child("mobile").getRef().setValue(phonee);
                        dataSnapshot.child("skills").getRef().push().child(ed.getText().toString());

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

    }
}
