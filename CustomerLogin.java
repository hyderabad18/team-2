package com.example.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.design.widget.Snackbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class CustomerLogin extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser currentUser;
    DatabaseReference rootref,rootUserref,newuserref;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    EditText ed1;
    Button b1;

    String mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        ed1=(EditText)findViewById(R.id.email);
        //ed2=(EditText)findViewById(R.id.login_pswd);

        auth=FirebaseAuth.getInstance();


        // currentUser=auth.getCurrentUser();
        b1=(Button)findViewById(R.id.login);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + ed1.getText().toString().trim(),
                        60,
                        TimeUnit.SECONDS,
                        TaskExecutors.MAIN_THREAD,
                        mCallbacks);
            }
        });
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            //Getting the code sent by SMS
            String code = phoneAuthCredential.getSmsCode();
            Toast.makeText(CustomerLogin.this,"Verification is under process",Toast.LENGTH_SHORT).show();

            //sometime the code is not detected automatically
            //in this case the code will be null
            //so user has to manually enter the code
            if (code != null) {
                ed1.setText(code);
                //verifying the code
                verifyVerificationCode(code);
            }
            else{
                rootref= FirebaseDatabase.getInstance().getReference();
                rootUserref=rootref.child("Users");
                newuserref=rootUserref.push().getRef();
                String key=newuserref.getKey();
                User u=new User(ed1.getText().toString(),"",0l,0l);
                newuserref.setValue(u);

                Intent intent = new Intent(CustomerLogin.this, CustomerHomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                editor = sharedPreferences.edit();
                editor.putString("type","user");
                editor.putString("mobile",ed1.getText().toString());
                editor.putString("key",key);
                editor.commit();
                finish();
                startActivity(intent);

            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(CustomerLogin.this, "Hello here"+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            Toast.makeText(CustomerLogin.this,"Code has been successfully sent to your mobile",Toast.LENGTH_SHORT).show();
            //storing the verification id that is sent to the user
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        //creating the credential
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);


        Toast.makeText(CustomerLogin.this,"Code has been verified",Toast.LENGTH_SHORT).show();
        //signing the user
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        auth.signInWithCredential(credential)
                .addOnCompleteListener(CustomerLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activityn

                            rootref= FirebaseDatabase.getInstance().getReference();
                            rootUserref=rootref.child("Users");
                            newuserref=rootUserref.push().getRef();
                            String key=newuserref.getKey();
                            User u=new User(ed1.getText().toString(),"",0l,0l);
                            newuserref.setValue(u);

                            Intent intent = new Intent(CustomerLogin.this, CustomerHomeActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            sharedPreferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                            editor = sharedPreferences.edit();
                            editor.putString("type","user");
                            editor.putString("mobile",ed1.getText().toString());
                            editor.putString("key",key);
                            editor.commit();
                            finish();
                            startActivity(intent);

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                            Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();
                        }
                    }
                });
    }
}
