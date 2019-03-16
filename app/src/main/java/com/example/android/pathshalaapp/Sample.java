package com.example.android.pathshalaapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.pathshalaapp.chatroom.ChatRoom_studs;

public class Sample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);
    }

    public void ChatRoom(View view)
    {
        startActivity(new Intent(this, ChatRoom_studs.class));
    }
}
