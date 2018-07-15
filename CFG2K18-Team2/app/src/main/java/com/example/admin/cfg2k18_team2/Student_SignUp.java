package com.example.admin.cfg2k18_team2;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by admin on 7/15/2018.
 */

public class Student_SignUp extends AppCompatActivity
{
    Button b1;
    String selectedCourse,selectedBranch,selectedYear;
    DatabaseReference rootstudentref,studentref;
    FirebaseAuth auth;
    FirebaseDatabase currentUser;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
   // TextView name,phone,email,tcourse;

    String name,email,phone,pwd,course,branch,year,key;

    EditText ed1,ed2,ed3,ed4,ed5,ed6,ed7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ed1=(EditText) findViewById(R.id.name);
        ed2=(EditText) findViewById(R.id.email);
        ed3=(EditText) findViewById(R.id.phone);
        ed4=(EditText) findViewById(R.id.emailtxt);
        ed5=(EditText) findViewById(R.id.branch);
        ed6=(EditText)findViewById(R.id.year);
        ed7=(EditText)findViewById(R.id.Course);

        auth=FirebaseAuth.getInstance();

        rootstudentref=FirebaseDatabase.getInstance().getReference();
        studentref=rootstudentref.child("Students").getRef();





        b1=(Button)findViewById(R.id.done);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=ed1.getText().toString();
                email=ed2.getText().toString();
                phone=ed3.getText().toString();
                pwd=ed4.getText().toString();
                branch=ed5.getText().toString();
                year=ed6.getText().toString();
                course=ed7.getText().toString();
                if(!name.equals("") && !email.equals("") && !phone.equals("") && !pwd.equals("")){
                    auth.createUserWithEmailAndPassword(email,pwd).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            editor=sharedPreferences.edit();

                            studentref=rootstudentref.push().getRef();
                            key=studentref.getKey();
                            studentref.child("name").setValue(name);
                            studentref.child("email").setValue(email);
                            studentref.child("mobile").setValue(phone);
                            studentref.child("course").setValue(course);
                            studentref.child("branch").setValue(branch);
                            studentref.child("year").setValue(year);
                            studentref.child("downloads").setValue(0l);
                            editor.putString("type","student");
                            editor.putString("key",key);
                            editor.putString("email",email);
                            editor.commit();


                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(getApplicationContext(),"Please enter valid details",Toast.LENGTH_SHORT).show();;
                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext()," Invalid Credentials Please provide correct details",Toast.LENGTH_SHORT).show();;
                }


            }
        });
    }
}
