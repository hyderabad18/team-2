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

/**
 * Created by admin on 7/15/2018.
 */

public class Student_Login extends AppCompatActivity {
    private EditText emailAuth, passAuth;
    private TextView signUp;
    private Button login;
    FirebaseAuth auth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_login);

        emailAuth = (EditText) findViewById(R.id.input_email);
        passAuth = (EditText) findViewById(R.id.input_password);
        signUp = (TextView) findViewById(R.id.signup_link);
        login = (Button) findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          Intent in = new Intent(getApplicationContext(), Student_SignUp.class);
                                          startActivity(in);
                                          finish();

                                      }
                                  }
        );


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String email = emailAuth.getText().toString();
                final String password = passAuth.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Student_Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    // there was an error
                                    if (password.length() < 6) {
                                        Toast.makeText(Student_Login.this, "PAssword Problem", Toast.LENGTH_LONG).show();

                                    } else {
                                        Toast.makeText(Student_Login.this, "Failed", Toast.LENGTH_LONG).show();
                                    }
                                } else {

                                    Intent intent = new Intent(Student_Login.this, StudentHomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
        });

    }
}