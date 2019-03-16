package com.example.android.pathshalaapp.chatroom;

import android.app.ProgressDialog;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.android.pathshalaapp.R;
import com.example.android.pathshalaapp.otpverification;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ChatRoom_studs extends AppCompatActivity
{
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;
    ChildEventListener childEventListener;
    FirebaseStorage firebasestorage;
    StorageReference storageReference;

    String doc_url="";
    Uri doc_data=null;
    int capture=1000;
    RecyclerView recyclerView;
    Button send;
    ImageButton attacment;
    EditText e;
    ArrayList<ChatRoom_studs_pojo> arrayList;
    ChatRoom_studs_adapter chatroom_studs_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom_studs);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference("Chatroom_studs/");
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        firebasestorage = FirebaseStorage.getInstance();
        storageReference=firebasestorage.getReference().child("chatroom_studs/"+System.currentTimeMillis()+".jpg");

        send =findViewById(R.id.sendButton);
        attacment=findViewById(R.id.attachfile);
        e=findViewById(R.id.messageEditText);
        recyclerView=findViewById(R.id.recyclerview);
        arrayList=new ArrayList<>();
        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayout.VERTICAL,false));
        chatroom_studs_adapter=new ChatRoom_studs_adapter(ChatRoom_studs.this,arrayList);
        recyclerView.setAdapter(chatroom_studs_adapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final ProgressDialog progressDialog=new ProgressDialog(ChatRoom_studs.this);
                progressDialog.setMessage("Uploading");
                progressDialog.show();

                final String msg=e.getText().toString();
                if(doc_data==null)
                {
                    ChatRoom_studs_pojo insert = new ChatRoom_studs_pojo(msg, "", "");
                    databaseReference.push().setValue(insert);
                    e.setText("");
                    progressDialog.dismiss();
                }
                else
                {
                    storageReference.putFile(doc_data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                        {
                            progressDialog.dismiss();
                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri)
                                {
                                    Uri abc=uri;
                                    doc_url=abc.toString();
                                    ChatRoom_studs_pojo insert = new ChatRoom_studs_pojo(msg,"", doc_url);
                                    databaseReference.push().setValue(insert);
                                }
                            });
                            e.setText("");
                            doc_url="";
                            doc_data=null;
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e)
                                {
                                    progressDialog.dismiss();
                                    Toast.makeText(ChatRoom_studs.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            }
        });


        attacment.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,capture);
            }
        });

        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                ChatRoom_studs_pojo chat=dataSnapshot.getValue(ChatRoom_studs_pojo.class);
                arrayList.add(chat);
                chatroom_studs_adapter.notifyDataSetChanged();
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) { }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) { }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {  }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) { }
        };
        databaseReference.addChildEventListener(childEventListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if((requestCode==capture)&&(resultCode==RESULT_OK)&&(data!=null)&&(data.getData()!=null))
        {
            doc_data=data.getData();
        }
    }
}
