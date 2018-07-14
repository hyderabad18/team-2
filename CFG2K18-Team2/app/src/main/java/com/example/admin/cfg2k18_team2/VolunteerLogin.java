package com.example.admin.cfg2k18_team2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by admin on 7/14/2018.
 */

public class VolunteerLogin extends AppCompatActivity {
    private EditText inputEmail, inputPassword;
    FirebaseAuth auth;
    private Button login;
    private TextView signup;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        auth = FirebaseAuth.getInstance();

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






    }
}
