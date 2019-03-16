package com.example.android.pathshalaapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class facultyAdapter extends RecyclerView.Adapter<facultyAdapter.FacultyViewHolder> {

    private Context ctx;
    private List<facultyModelClass> facultyList;

    public facultyAdapter(Context ctx, List<facultyModelClass> facultyList) {
        this.ctx = ctx;
        this.facultyList = facultyList;
    }

    @NonNull
    @Override
    public facultyAdapter.FacultyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(ctx);
        View view=inflater.inflate(R.layout.facultylist,null,false);
        return new FacultyViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return facultyList.size();
    }

    @Override
    public void onBindViewHolder(@NonNull facultyAdapter.FacultyViewHolder facultyViewHolder, int i) {
        facultyModelClass faculty=  facultyList.get(i);
        facultyViewHolder.facultyname.setText(faculty.getName());
        facultyViewHolder.facultysubject.setText(faculty.getSubject());
        facultyViewHolder.facultyphonenumber.setText(faculty.getPhoneNumber());
        facultyViewHolder.facultyimage.setImageDrawable(ctx.getResources().getDrawable(faculty.getImage()));



    }




    public class FacultyViewHolder extends RecyclerView.ViewHolder {

        TextView facultyname,facultysubject,facultyphonenumber;
        ImageView facultyimage;


        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);

            facultyname=itemView.findViewById(R.id.facultyname);
            facultysubject=itemView.findViewById(R.id.facultysubject);
            facultyphonenumber=itemView.findViewById(R.id.facultyphone);
            facultyimage=itemView.findViewById(R.id.facultyphoto);
        }
    }
}
