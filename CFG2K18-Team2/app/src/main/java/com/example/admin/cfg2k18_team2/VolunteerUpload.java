package com.example.admin.cfg2k18_team2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;

/**
 * Created by admin on 7/14/2018.
 */

public class VolunteerUpload extends MainActivity
{
    EditText title, author, edition;
    Button upload, picker;
    String titleString,authorname,editionString;

    static int Audio=1;
    String volunteername;



    FirebaseAuth auth;
    FirebaseUser currentUser;
    DatabaseReference rootVolunteerref,Volunteerref;
    StorageReference st;
    String key;
    private Uri uri;
    private EditText tags;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        rootVolunteerref = FirebaseDatabase.getInstance().getReference();
        Volunteerref = rootVolunteerref.child("Volunteers").getRef();
        auth =FirebaseAuth.getInstance();
        currentUser = auth.getCurrentUser();
        Volunteerref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    key = ds.getKey();
                    if(ds.child("email").getValue(String.class).toString().equals(currentUser.getEmail().toString()))
                    {
                        volunteername=ds.child("name").getValue(String.class);
                        break;
                    }
                }

                //Toast.makeText(VolunteerUpload.this, key , Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.content_frame);
        getLayoutInflater().inflate(R.layout.activity_volunteer_upload, contentFrameLayout);

        title = (EditText) findViewById(R.id.titlevolunteer);
        author = (EditText) findViewById(R.id.authorvolunteer);
        edition = (EditText) findViewById(R.id.editionvolunteer);
        upload = (Button) findViewById(R.id.uploadfilevolunteer);
        picker = (Button) findViewById(R.id.selectfilevolunteer);
        tags = (EditText) findViewById(R.id.tags);

        picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Intent i=new Intent();
                i.setType("audio/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(i, "Select Audio"), Audio);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if (uri != null) {
                    //  usercount=new Long(0);
                    st = FirebaseStorage.getInstance().getReference().child("audio/" + uri.getLastPathSegment());
                    //   st= FirebaseStorage.getInstance().getReference().child("audio/"+uri.getLastPathSegment());
                /*    UploadTask task=st.putFile(uri);
                    task.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            String file=taskSnapshot.getStorage().getDownloadUrl().toString();
                            Toast.makeText(UPloading.this,"Hello vivy"+file,Toast.LENGTH_SHORT).show();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                        }
                    });*/

                    st.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {

                            String str = taskSnapshot.getDownloadUrl().toString();

                            Toast.makeText(getApplicationContext(),"Uploaded Successfully",Toast.LENGTH_LONG).show();

                            DatabaseReference refer = Volunteerref.child(key).getRef();

                            refer = refer.child("Uploads").getRef();
                            RequestVolunteers req=new RequestVolunteers(volunteername,title.getText().toString(),author.getText().toString(),edition.getText().toString(),tags.getText().toString(),str);
                            DatabaseReference book=refer.push().getRef();
                            book.setValue(req);




                         /*   rootuserref.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    usercount= dataSnapshot.getChildrenCount();
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });*/
                          /*  String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date());
                            Card card=new Card(ed1.getText().toString(),ed2.getText().toString(),new Long(200),new Long(500),timeStamp,taskSnapshot.getDownloadUrl().toString());
                            Map<String,Object> m=new HashMap<>();
                            m.put("User"+usercount+1,card);
                            rootuserref.updateChildren(m).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(PostActivity2.this,"Successfully recorded",Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(PostActivity2.this,"Failed to record",Toast.LENGTH_SHORT).show();
                                }
                            });*/
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(VolunteerUpload.this, "Upload Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            uri=data.getData();
              Toast.makeText(VolunteerUpload.this,"File has been selected",Toast.LENGTH_SHORT).show();

        }

    }

    public void download(){
        try {
            File localFile  = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "New_Audio.mp3");
            localFile .createNewFile();
            // down.getFile(localFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
