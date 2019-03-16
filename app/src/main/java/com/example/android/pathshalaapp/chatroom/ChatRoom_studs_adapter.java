package com.example.android.pathshalaapp.chatroom;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.pathshalaapp.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ChatRoom_studs_adapter extends RecyclerView.Adapter<ChatRoom_studs_adapter.ViewHolder>
{

    Context context;
    ArrayList<ChatRoom_studs_pojo> arrayList;
    ChildEventListener childEventListener;

    public ChatRoom_studs_adapter(Context context, ArrayList<ChatRoom_studs_pojo> arrayList)
    {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatroom_message_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position)
    {
        ChatRoom_studs_pojo pojo=arrayList.get(position);
        String username=pojo.getUsername();
        String message=pojo.getMessage();
        String url=pojo.getPhoto_url();

        if(!url.isEmpty())
        {
            Picasso.get().load(url).into(viewHolder.imageView);
        }
        viewHolder.message.setText(message);
        viewHolder.username.setText(username);

        childEventListener=new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s)
            {
                ChatRoom_studs_pojo chat=dataSnapshot.getValue(ChatRoom_studs_pojo.class);
                arrayList.add(chat);
                notifyItemChanged(arrayList.size());
            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {            }
            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {            }
            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {            }
        };

    }

    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        TextView username,message;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imageView=itemView.findViewById(R.id.chat_image);
            username=itemView.findViewById(R.id.chat_name);
            message=itemView.findViewById(R.id.chat_text);
        }
    }
}
