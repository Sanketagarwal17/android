package com.example.android.pathshalaapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class faculty extends AppCompatActivity {




    List<facultyModelClass> facultyList;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.facultyrecyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    facultyList=new ArrayList<>();


        facultyList.add( new facultyModelClass(
             " sanket " , " science " , " 9879580784 " , R.drawable.ic_launcher_background ));

        facultyAdapter facultyadapter=new facultyAdapter(faculty.this,facultyList);
        recyclerView.setAdapter(facultyadapter);
    }

}
