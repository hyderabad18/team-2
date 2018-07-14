package com.example.rohith.firebase;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tv1,tv2;
    Button b1;

    FirebaseAuth auth;
    FirebaseUser currentUser;

    DatabaseReference rootVolunteerref,rootVolunteer;
    String email,pwd;
    String key;
    int f=0;
    auth=FirebaseAuth.getInstance();
    currentUser=auth.getCurrentUser();
    tv1=(TextView)findViewById(R.id.emaillogin);
    tv2=(TextView)findViewById(R.id.passwordlogin);

    b1=(Button)findViewById(R.id.signinlogin);

    rootVolunteerref=FirebaseDatabase.getInstance().getReference().child("Volunteers").getRef();

        b1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            email=tv1.getText().toString();
            pwd=tv2.getText().toString();
            if(!email.equals("") && !pwd.equals("")){
                auth.signInWithEmailAndPassword(email,pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //  rootVolunteer=rootVolunteerref.child();

                        f=0;
                        rootVolunteerref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                key=dataSnapshot.getKey();
                                for(DataSnapshot ds:dataSnapshot.getChildren()){
                                    ds.child("email").equals(email);
                                    f=1;
                                    break;
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        if(f==1){
                            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            editor =sharedPreferences.edit();
                            editor.putString("type","volunteer");
                            editor.putString("email",email);
                            editor.putString("key",key);
                            editor.commit();
                            Intent i=new Intent(LoginActivity.this,VolunteerHomeActivity.class);
                            finish();
                            startActivity(i);

                        }
                        else{
                            auth.signOut();
                            tv1.setText("");
                            tv2.setText("");
                            Toast.makeText(LoginActivity.this,"Invalid credentials Not a Volunteer",Toast.LENGTH_SHORT).show();
                        }

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
            }
            else{
                Toast.makeText(LoginActivity.this,"Invalid credentials PLease enter all your details",Toast.LENGTH_SHORT).show();

            }
        }
    });
}
