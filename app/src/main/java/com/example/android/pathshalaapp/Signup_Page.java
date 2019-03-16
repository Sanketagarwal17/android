package com.example.android.pathshalaapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Page extends AppCompatActivity
{
    EditText username,password;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__page);
        username=findViewById(R.id.signup_email);
        password=findViewById(R.id.signup_password);
        firebaseAuth=FirebaseAuth.getInstance();
    }

    public void SignUp(View view)
    {
        String email=username.getText().toString();
        final String pass=password.getText().toString();

        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task)
            {
                progressDialog.dismiss();
                if(task.isSuccessful())
                {
                    finish();
                    Toast.makeText(Signup_Page.this,"Successfully registered",Toast.LENGTH_SHORT).show();
                    username.setText("");
                    password.setText("");
                    startActivity(new Intent(Signup_Page.this,LogIn_Page.class));
                }
                else
                    Toast.makeText(Signup_Page.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void login_screen(View view) {
        startActivity(new Intent(this,LogIn_Page.class));
    }
}
