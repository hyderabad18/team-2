package com.example.admin.cfg2k18_team2;

import android.content.Intent;
import android.os.Bundle;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by admin on 7/14/2018.
 */

public class VolunteerSignUp extends AppCompatActivity
{
    private EditText inputEmail,inputPassword,inputName,inputPhone,inputSkills;
    private Button signUp;
    private TextView login;
    private FirebaseAuth auth;
    private boolean status = true;

    DatabaseReference dref;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();

        dref = FirebaseDatabase.getInstance().getReference();

        inputEmail = (EditText) findViewById(R.id.input_email);
        inputPassword = (EditText) findViewById(R.id.input_password);
        signUp = (Button) findViewById(R.id.btn_signup);
        login = (TextView) findViewById(R.id.login_link);
        inputPhone = (EditText) findViewById(R.id.input_phone);
        inputSkills=(EditText) findViewById(R.id.input_skills);
        inputName = (EditText) findViewById(R.id.input_name);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent in = new Intent(getApplicationContext(),VolunteerLogin.class);
                startActivity(in);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String email = inputEmail.getText().toString().trim();
                final String password = inputPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Toast.makeText(getApplicationContext(), "Creating an Account",Toast.LENGTH_LONG).show();

                //create user
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(VolunteerSignUp.this, new OnCompleteListener<AuthResult>()
                        {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(getApplicationContext(), "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                } else

                                {
                                    Intent in = new Intent(getApplicationContext(),VolunteerLogin.class);
                                    startActivity(in);
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}
