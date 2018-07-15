package com.example.admin.cfg2k18_team2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by admin on 7/14/2018.
 */

public class VolunteerLogin extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    FirebaseAuth auth;
    FirebaseUser currentUser;
    private Button login;
    private TextView signup;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    int f=0;
    String key="";

    DatabaseReference rootVolunteerref,volunteerref;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volunteer_login);


        auth = FirebaseAuth.getInstance();
        rootVolunteerref= FirebaseDatabase.getInstance().getReference();
        volunteerref = rootVolunteerref.child("Volunteers").getRef();
        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        login = (Button) findViewById(R.id.btn_login);
        signup = (TextView) findViewById(R.id.signup_link);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), VolunteerSignUp.class);
                startActivity(in);
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = inputEmail.getText().toString();
                final String password = inputPassword.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(VolunteerLogin.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(getApplicationContext(), "FAILED", Toast.LENGTH_LONG).show();
                                    }
                                } else

                                {
                                    volunteerref.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot)
                                        {
                                            for(DataSnapshot ds:dataSnapshot.getChildren())
                                            {
                                                key = ds.getKey();
                                               if( ds.child("email").getValue().equals(email)){
                                                  // f=1;
                                                   break;

                                                }
                                            }
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }

                                    });
                                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                                        editor = sharedPreferences.edit();
                                        editor.putString("type", "volunteer");
                                        editor.putString("key", key);
                                        editor.putString("email", email);
                                        editor.commit();
                                    Toast.makeText(VolunteerLogin.this,"Logging in",Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(VolunteerLogin.this, VolunteerUpload.class);
                                        startActivity(intent);
                                        finish();
                                    }
                            }
                        });
            }
        });

    }

}




