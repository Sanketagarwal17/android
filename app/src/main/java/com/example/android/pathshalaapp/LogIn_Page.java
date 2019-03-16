package com.example.android.pathshalaapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn_Page extends AppCompatActivity {
    EditText username, password;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        username = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public void login(View view) {
        String user = username.getText().toString().trim();
        String pass = password.getText().toString().trim();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(user, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if (task.isSuccessful()) {
                            Toast.makeText(LogIn_Page.this, "Logged In", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(LogIn_Page.this,Sample.class));
                        } else
                            Toast.makeText(LogIn_Page.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void signup_screen(View view) {
        finish();
        startActivity(new Intent(LogIn_Page.this, Signup_Page.class));
    }

}