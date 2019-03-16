package com.example.android.pathshalaapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

import java.util.concurrent.TimeUnit;



public class otpverification extends AppCompatActivity {
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallback;
    FirebaseAuth firebaseAuth;
    EditText otp;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        StartFirebaseLogin();

         String input=otp.getText().toString();
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(code,input);
        SigninWithPhone(credential);



    String mobilenumber=getIntent().getExtras().getString("usermobilenumber");
        firebaseAuth=FirebaseAuth.getInstance();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                mobilenumber,                     // Phone number to verify
                60,                           // Timeout duration
                TimeUnit.SECONDS,                // Unit of timeout
                otpverification.this,        // Activity (for callback binding)
                mCallback);
    }


    private void StartFirebaseLogin() {
        firebaseAuth = FirebaseAuth.getInstance();
        mCallback = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(otpverification.this,"verification completed",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(otpverification.this,Signup_Page.class));
            }
            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(otpverification.this,"verification fialed",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                code = s;
                Toast.makeText(otpverification.this,"Code sent",Toast.LENGTH_SHORT).show();
            }
        };
    }







        private void SigninWithPhone (PhoneAuthCredential credential){
            firebaseAuth.signInWithCredential(credential)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(otpverification.this, Signup_Page.class));
                                finish();
                            } else {
                                Toast.makeText(otpverification.this, "Incorrect OTP", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }









    }